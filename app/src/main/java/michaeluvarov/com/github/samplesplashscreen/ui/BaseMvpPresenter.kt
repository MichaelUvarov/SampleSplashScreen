package michaeluvarov.com.github.samplesplashscreen.ui

import android.app.Activity
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView

abstract class BaseMvpPresenter<View : MvpView> : MvpPresenter<View>() {

    @set:JvmName("_setActivity")
    private lateinit var activity: Activity

    abstract val router: Any

    fun setActivity(activity: Activity) {
        this.activity = activity
        initRouter(activity)

        if (router !is BaseRouter)
            throw Exception("Router need extends BaseRouter")
    }

    abstract fun initRouter(activity: Activity)
}