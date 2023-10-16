package top.yumik.app.starcloud

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ScApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        BuildConfig.VERSION_NAME
    }
}