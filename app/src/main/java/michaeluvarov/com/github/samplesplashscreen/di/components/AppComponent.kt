package michaeluvarov.com.github.samplesplashscreen.di.components

import com.arellomobile.mvp.MvpView
import dagger.Component
import michaeluvarov.com.github.samplesplashscreen.di.modules.ContextModule
import michaeluvarov.com.github.samplesplashscreen.ui.BaseRxMvpPresenter
import michaeluvarov.com.github.samplesplashscreen.ui.activity.splash.SplashScreenPresenter


@Component(modules = arrayOf(ContextModule::class))
interface AppComponent {
    fun inject(baseRxMvpPresenter: BaseRxMvpPresenter<MvpView>)

    fun inject(splashScreenPresenter: SplashScreenPresenter)
}