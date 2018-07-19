package michaeluvarov.com.github.samplesplashscreen.ui.activity.splash

import android.app.Activity
import com.arellomobile.mvp.InjectViewState
import michaeluvarov.com.github.samplesplashscreen.App
import michaeluvarov.com.github.samplesplashscreen.ui.BaseMvpPresenter

@InjectViewState
class SplashScreenPresenter : BaseMvpPresenter<SplashScreenContract.View>() {

    override lateinit var router: SplashScreenContract.Router

    init {
        App.graph.inject(this)
    }

    override fun initRouter(activity: Activity) {
        this.router = SplashScreenRouter(activity)
    }

    fun onLoadedSplashFinished() {
        //TODO Add dynamic loading of libraries or logic to move to the next screen
        router.openLoginActivity()
    }
}