package com.example.giffer.api

import com.example.giffer.model.MainGif
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {

    @GET("search")
    suspend fun getGifs(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int,
        @Query("q") category: String
    ): Response<MainGif>

    @GET("trending")
    suspend fun getTrendGifs(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int
    ): Response<MainGif>

}