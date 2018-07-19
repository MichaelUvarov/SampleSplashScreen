package michaeluvarov.com.github.samplesplashscreen.ui

import android.app.Activity
import android.widget.Toast

fun Activity.toast(resId: Int) {
    Toast.makeText(this, resId, Toast.LENGTH_LONG).show()
}

fun Activity.toast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}