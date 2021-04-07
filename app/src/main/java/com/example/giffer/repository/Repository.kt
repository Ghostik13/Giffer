package com.example.giffer.repository

import com.example.giffer.api.GiphyApi
import com.example.giffer.model.MainGif
import com.example.giffer.util.Constants.Companion.ANDROID_SDK_KEY
import retrofit2.Response

class Repository(private val api: GiphyApi) {

    suspend fun getGifs(query: String): Response<MainGif> {
        return api.getGifs(ANDROID_SDK_KEY, 50, query)
    }

    suspend fun getTrendGifs(): Response<MainGif> {
        return api.getTrendGifs(ANDROID_SDK_KEY, 50)
    }
}