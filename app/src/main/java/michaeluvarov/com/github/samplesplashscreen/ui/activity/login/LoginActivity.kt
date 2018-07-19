package michaeluvarov.com.github.samplesplashscreen.ui.activity.login

import android.animation.Animator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.InputType
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.kogitune.activity_transition.ActivityTransition
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import michaeluvarov.com.github.samplesplashscreen.R
import michaeluvarov.com.github.samplesplashscreen.ui.BaseMvpActivity
import michaeluvarov.com.github.samplesplashscreen.ui.toast
import michaeluvarov.com.github.samplesplashscreen.ui.utils.Constants
import michaeluvarov.com.github.samplesplashscreen.ui.utils.SimpleTextWatcher

class LoginActivity : BaseMvpActivity(), LoginActivityContract.View {

    companion object {
        private const val ANIMATE_KEY: String = "args_need_animate"

        fun getIntent(context: Context, isNeedAnimate: Boolean, isClosePrevious: Boolean = true): Intent {
            val intent = Intent(context, LoginActivity::class.java)
            if (isClosePrevious)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra(ANIMATE_KEY, isNeedAnimate)
            return intent
        }
    }

    @InjectPresenter
    override lateinit var presenter: LoginPresenter

    @ProvidePresenter
    fun provideLoginPresenter(): LoginPresenter {
        val loginPresenter = LoginPresenter()
        loginPresenter.setActivity(this)
        return loginPresenter
    }

    private val animationListener = object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {
        }

        override fun onAnimationEnd(animation: Animator?) {
            presenter.onLoginLoaded()
        }

        override fun onAnimationCancel(animation: Animator?) {
        }

        override fun onAnimationStart(animation: Animator?) {
        }
    }

    private val loginDataWatcher = object : SimpleTextWatcher() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            super.onTextChanged(s, start, before, count)
            presenter.onLoginDataChanged(login_edt_txt.text.toString(), password_edt_txt.text.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null
                && intent != null
                && intent.getBooleanExtra(ANIMATE_KEY, false)) {
            ActivityTransition.with(intent)
                    .to(logo_img)
                    .duration(Constants.delayAnimation.toInt())
                    .enterListener(animationListener)
                    .start(savedInstanceState)
        } else {
            presenter.onLoginLoaded()
        }

        initClick()
        initTextChangedListener()
        initToolbar()
    }

    private fun initClick() {
        password_img.setOnClickListener { presenter.onPasswordImgClick() }
        login_btn.setOnClickListener { presenter.onLoginClick() }
    }

    private fun initTextChangedListener() {
        presenter.onLoginDataChanged(login_edt_txt.text.toString(), password_edt_txt.text.toString())

        login_edt_txt.addTextChangedListener(loginDataWatcher)
        password_edt_txt.addTextChangedListener(loginDataWatcher)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = null
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.navigationIcon = null
        toolbar_title.text = getString(R.string.login_toolbar_title)
    }

    override fun changeVisibilityPassword(passwordVisibility: Int) {
        if (passwordVisibility == View.VISIBLE) {
            password_edt_txt.inputType = InputType.TYPE_CLASS_TEXT
            password_img.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_visibility_off_black))
        } else {
            password_edt_txt.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            password_img.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_visibility_black))
        }
    }

    override fun changeEnabledLoginButton(isEnabled: Boolean) {
        login_btn.isEnabled = isEnabled
    }

    override fun showLoginResult(message: String) {
        toast(message)
    }
}