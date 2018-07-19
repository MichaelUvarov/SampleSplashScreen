package michaeluvarov.com.github.samplesplashscreen.ui

import android.content.Context
import com.arellomobile.mvp.MvpView
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import michaeluvarov.com.github.samplesplashscreen.App
import michaeluvarov.com.github.samplesplashscreen.di.annotations.ApplicationContext
import javax.inject.Inject

abstract class BaseRxMvpPresenter<View : MvpView> : BaseMvpPresenter<View>() {

    @Inject
    @field:ApplicationContext
    lateinit var context: Context

    init {
        App.graph.inject(this as BaseRxMvpPresenter<MvpView>)
    }

    private val compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    protected fun <T> subscribeSingle(single: Single<T>, onSuccess: Consumer<T>, onError: Consumer<Throwable>, isDefaultSubscribeOn: Boolean = true) {
        if (isDefaultSubscribeOn) {
            disposeOnDestroy(single
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(onSuccess, onError))
        } else
            disposeOnDestroy(single.subscribe(onSuccess, onError))
    }

    protected fun <T> subscribeFlowable(flowable: Flowable<T>, onSuccess: Consumer<T>, onError: Consumer<Throwable>, isDefaultSubscribeOn: Boolean = true) {
        if (isDefaultSubscribeOn) {
            disposeOnDestroy(flowable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(onSuccess, onError))
        } else
            disposeOnDestroy(flowable.subscribe(onSuccess, onError))
    }

    protected fun <T> subscribeFlowable(flowable: Flowable<T>, onSuccess: Consumer<T>, onError: (Throwable) -> Unit, isDefaultSubscribeOn: Boolean = true) {
        subscribeFlowable(flowable, onSuccess, Consumer { t: Throwable -> onError(t) }, isDefaultSubscribeOn)
    }

    private fun disposeOnDestroy(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}