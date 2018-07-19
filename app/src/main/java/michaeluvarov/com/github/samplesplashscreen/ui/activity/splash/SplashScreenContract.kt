package michaeluvarov.com.github.samplesplashscreen.ui.activity.splash

import com.arellomobile.mvp.MvpView

interface SplashScreenContract {
    interface View : MvpView

    interface Router {
        fun openLoginActivity()
    }
}