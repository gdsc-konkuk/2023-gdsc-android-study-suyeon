package kr.ac.konkuk.gdsc.gdscsuyeon

import dagger.hilt.android.HiltAndroidApp
import android.app.Application

@HiltAndroidApp
class Application : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}