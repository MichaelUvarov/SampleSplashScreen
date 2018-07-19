package michaeluvarov.com.github.samplesplashscreen.ui.activity.splash

import android.os.Bundle
import android.os.Handler
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import michaeluvarov.com.github.samplesplashscreen.R
import michaeluvarov.com.github.samplesplashscreen.ui.BaseMvpActivity
import michaeluvarov.com.github.samplesplashscreen.ui.utils.Constants

class SplashScreenActivity : BaseMvpActivity(), SplashScreenContract.View {

    @InjectPresenter
    override lateinit var presenter: SplashScreenPresenter

    @ProvidePresenter
    fun provideSplashScreenPresenter(): SplashScreenPresenter {
        val splashScreenPresenter = SplashScreenPresenter()
        splashScreenPresenter.setActivity(this)
        return splashScreenPresenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_NoActionBar)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash_screen)
    }

    override fun onStart() {
        super.onStart()

        Handler().postDelayed({
            presenter.onLoadedSplashFinished()
        }, Constants.delayUpdateUI)
    }
}
