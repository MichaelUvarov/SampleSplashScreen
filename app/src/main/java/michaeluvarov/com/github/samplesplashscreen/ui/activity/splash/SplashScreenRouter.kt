package michaeluvarov.com.github.samplesplashscreen.ui.activity.splash

import android.app.Activity
import com.kogitune.activity_transition.ActivityTransitionLauncher
import kotlinx.android.synthetic.main.activity_splash_screen.*
import michaeluvarov.com.github.samplesplashscreen.ui.BaseRouter
import michaeluvarov.com.github.samplesplashscreen.ui.activity.login.LoginActivity

class SplashScreenRouter(activity: Activity) : BaseRouter(activity), SplashScreenContract.Router {

    override fun openLoginActivity() {
        val intent = LoginActivity.getIntent(activity, true, false)
        ActivityTransitionLauncher.with(activity).from(activity.logo_img).launch(intent)
        //finish after launch solves the problem with blank screen during the transition
        activity.finish()
    }
}