package com.example.giffer.framework.remote

import com.example.giffer.app.main.data.model.GifRemote
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {

    @GET("search")
    suspend fun getGifs(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int,
        @Query("q") category: String
    ): GifRemote

    @GET("trending")
    suspend fun getTrendGifs(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int
    ): GifRemote
}