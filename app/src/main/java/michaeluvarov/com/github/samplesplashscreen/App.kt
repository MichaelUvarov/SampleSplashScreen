package michaeluvarov.com.github.samplesplashscreen

import android.app.Application
import android.support.v7.app.AppCompatDelegate
import michaeluvarov.com.github.samplesplashscreen.di.components.AppComponent
import michaeluvarov.com.github.samplesplashscreen.di.components.DaggerAppComponent
import michaeluvarov.com.github.samplesplashscreen.di.modules.ContextModule

class App : Application() {

    companion object {
        lateinit var graph: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        graph = DaggerAppComponent.builder()
                .contextModule(ContextModule(this))
                .build()
    }
}