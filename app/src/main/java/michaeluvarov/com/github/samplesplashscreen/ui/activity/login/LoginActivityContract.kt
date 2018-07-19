package michaeluvarov.com.github.samplesplashscreen.ui.activity.login

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import michaeluvarov.com.github.samplesplashscreen.ui.interfaces.MessageMvpView

interface LoginActivityContract {
    interface View : MessageMvpView {
        @StateStrategyType(AddToEndSingleStrategy::class)
        fun changeVisibilityPassword(passwordVisibility: Int)

        @StateStrategyType(AddToEndSingleStrategy::class)
        fun changeEnabledLoginButton(isEnabled: Boolean)

        @StateStrategyType(OneExecutionStateStrategy::class)
        fun showLoginResult(message: String)
    }

    interface Router
}