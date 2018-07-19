package michaeluvarov.com.github.samplesplashscreen.ui.interfaces

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface MessageMvpView : MvpView {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showMessage(resId: Int)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showMessage(message: String?)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showErrorMessage(resId: Int)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showErrorMessage(message: String?)
}