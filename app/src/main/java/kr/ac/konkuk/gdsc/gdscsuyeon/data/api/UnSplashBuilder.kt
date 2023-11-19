package kr.ac.konkuk.gdsc.gdscsuyeon.data.api

import android.provider.SyncStateContract.Constants
import kr.ac.konkuk.gdsc.gdscsuyeon.data.model.PhotoUrl
import kr.ac.konkuk.gdsc.gdscsuyeon.util.UNSPLASH
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object UnSplashBuilder {
    var api: UnSplashService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.unsplash.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
            .build()

        api = retrofit.create(UnSplashService::class.java)
    }
}