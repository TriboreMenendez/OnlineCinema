package tribore.onlinecinema

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import tribore.onlinecinema.data.di.dataModule
import tribore.onlinecinema.ui.di.appModule

class CinemaApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CinemaApplication)
            modules(appModule, dataModule)
        }
    }
}