package com.example.giffer.repository

import com.example.giffer.api.RetrofitInstance
import com.example.giffer.model.MainGif
import com.example.giffer.util.Constants.Companion.ANDROID_SDK_KEY
import retrofit2.Response

class Repository(private val retrofit: RetrofitInstance) {

    suspend fun getGifs(query: String): Response<MainGif> {
        return retrofit.api.getGifs(ANDROID_SDK_KEY, 50, query)
    }

    suspend fun getTrendGifs(): Response<MainGif> {
        return retrofit.api.getTrendGifs(ANDROID_SDK_KEY, 50)
    }
}