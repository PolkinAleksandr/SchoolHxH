package ru.panmin.lesson9

import android.app.Application
import com.yandex.mapkit.MapKitFactory

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey(BuildConfig.MAPKIT_API_KEY)
    }
}
