package artur.projekt1

import android.app.Application
import timber.log.Timber

class Projekt1Application : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}