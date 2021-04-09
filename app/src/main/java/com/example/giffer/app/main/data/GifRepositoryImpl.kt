package com.example.giffer.app.main.data

import com.example.giffer.app.main.data.model.GifRemote
import com.example.giffer.app.main.domain.GifRepository
import com.example.giffer.framework.remote.GiphyApi
import com.example.giffer.util.Constants.Companion.ANDROID_SDK_KEY

class GifRepositoryImpl(private val api: GiphyApi): GifRepository {

    override suspend fun loadGifs(query: String): GifRemote {
        return api.getGifs(ANDROID_SDK_KEY, 50, query)
    }

    override suspend fun loadTrendGifs(): GifRemote {
        return api.getTrendGifs(ANDROID_SDK_KEY, 50)
    }
}