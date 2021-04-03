package com.example.giffer.model

data class MainGif(
    val data: List<Data>
)

data class Data(
    val analytics_response_payload: String,
    val bitly_gif_url: String,
    val bitly_url: String,
    val content_url: String,
    val embed_url: String,
    val id: String,
    val images: Images,
    val import_datetime: String,
    val is_sticker: Int,
    val rating: String,
    val slug: String,
    val source: String,
    val source_post_url: String,
    val source_tld: String,
    val title: String,
    val trending_datetime: String,
    val type: String,
    val url: String,
    val username: String
)

data class Images(
    val original: Original
)

data class Original(
    val frames: String,
    val hash: String,
    val height: String,
    val mp4: String,
    val mp4_size: String,
    val size: String,
    val url: String,
    val webp: String,
    val webp_size: String,
    val width: String
)

