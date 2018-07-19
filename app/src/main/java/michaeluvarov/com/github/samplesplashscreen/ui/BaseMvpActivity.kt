package michaeluvarov.com.github.samplesplashscreen.ui

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import michaeluvarov.com.github.samplesplashscreen.ui.interfaces.MessageMvpView

abstract class BaseMvpActivity : MvpAppCompatActivity(), MessageMvpView {

    abstract val presenter: Any

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (presenter !is BaseMvpPresenter<*>) {
            throw Exception("Presenter need extends BaseMvpPresenter")
        }
    }

    override fun showMessage(resId: Int) {
        toast(resId)
    }

    override fun showMessage(message: String?) {
        toast(message)
    }

    override fun showErrorMessage(resId: Int) {
        toast(resId)
    }

    override fun showErrorMessage(message: String?) {
        toast(message)
    }
}
