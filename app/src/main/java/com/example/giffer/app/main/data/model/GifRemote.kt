package com.example.giffer.app.main.data.model

data class GifRemote(
    val data: List<Data>
)

data class Data(
    val id: String,
    val images: Images,
    val rating: String,
    val title: String,
    val type: String,
    val url: String,
    val username: String
)

data class Images(
    val original: Original
)

data class Original(
    val hash: String,
    val size: String,
    val url: String,
    val webp: String
)

