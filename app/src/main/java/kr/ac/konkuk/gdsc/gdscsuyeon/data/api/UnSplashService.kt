package kr.ac.konkuk.gdsc.gdscsuyeon.data.api

import kr.ac.konkuk.gdsc.gdscsuyeon.BuildConfig
import kr.ac.konkuk.gdsc.gdscsuyeon.data.model.PhotoUrlResponse
import kr.ac.konkuk.gdsc.gdscsuyeon.util.UNSPLASH
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface UnSplashService {
    companion object {
        const val CLIENT_ID = BuildConfig.UNSPLASH_ACCESS_KEY
    }

    @GET("photos/random/?client_id=$CLIENT_ID&count=1")
    fun getRandomPhotoUrl(): Call<List<PhotoUrlResponse>>
}