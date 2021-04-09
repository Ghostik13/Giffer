package com.example.giffer.app.main.domain

import com.example.giffer.app.main.data.model.GifRemote

interface GifRepository {
    suspend fun loadGifs(query: String): GifRemote
    suspend fun loadTrendGifs(): GifRemote
}