package michaeluvarov.com.github.samplesplashscreen.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import michaeluvarov.com.github.samplesplashscreen.di.annotations.ApplicationContext

@Module
class ContextModule(private val app: Application) {

    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return app
    }
}
