package michaeluvarov.com.github.samplesplashscreen.ui.activity.login

import android.app.Activity
import android.view.View
import com.arellomobile.mvp.InjectViewState
import michaeluvarov.com.github.samplesplashscreen.R
import michaeluvarov.com.github.samplesplashscreen.ui.BaseRxMvpPresenter

@InjectViewState
class LoginPresenter : BaseRxMvpPresenter<LoginActivityContract.View>() {

    override lateinit var router: LoginActivityContract.Router

    private var isPasswordVisibility: Int = View.INVISIBLE

    override fun initRouter(activity: Activity) {
        this.router = LoginRouter(activity)
    }

    fun onLoginLoaded() {
        viewState.changeVisibilityPassword(isPasswordVisibility)
    }

    fun onPasswordImgClick() {
        if (isPasswordVisibility == View.VISIBLE)
            isPasswordVisibility = View.GONE
        else
            isPasswordVisibility = View.VISIBLE

        viewState.changeVisibilityPassword(isPasswordVisibility)
    }

    fun onLoginDataChanged(login: String, password: String) {
        if (login.isNotEmpty() && password.isNotEmpty())
            viewState.changeEnabledLoginButton(true)
        else
            viewState.changeEnabledLoginButton(false)
    }

    fun onLoginClick() {
        viewState.showLoginResult(context.getString(R.string.result_success))
        //TODO show next activity
    }
}