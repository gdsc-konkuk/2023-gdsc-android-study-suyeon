package kr.ac.konkuk.gdsc.gdscsuyeon

import dagger.hilt.android.HiltAndroidApp
import android.app.Application

@HiltAndroidApp
class Application : Application() {
    override fun onCreate() {
        super.onCreate()
//        // Application에서 TodoRepository에 대한 의존성 주입
//        DaggerAppComponent.builder()
//            .application(this)
//            .build()
//            .inject(this)
    }
}