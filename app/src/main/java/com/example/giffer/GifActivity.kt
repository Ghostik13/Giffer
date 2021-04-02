package com.example.giffer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.giffer.util.Constants.Companion.CURRENT_GIF_TITLE
import com.example.giffer.util.Constants.Companion.CURRENT_GIF_URL
import com.example.giffer.util.Constants.Companion.SHARE_GIF
import kotlinx.android.synthetic.main.activity_gif.*
import java.lang.StringBuilder

class GifActivity : AppCompatActivity() {

    private var currentGif: String? = null
    private var currentGifTitle: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gif)

        val bundle = intent.extras
        if (bundle != null) {
            currentGif = bundle.getString(CURRENT_GIF_URL)
            currentGifTitle = bundle.getString(CURRENT_GIF_TITLE)

        }
        currentGif?.let {
            Glide
                .with(this)
                .load(it)
                .into(gif_main)
        }
        currentGifTitle?.let {
            gif_title.text = it
        }
        val sendIntent = Intent(Intent.ACTION_SEND)
        sendIntent.putExtra(Intent.EXTRA_TEXT, currentGif.toString())
        sendIntent.type = "text/plain"

        share_btn.setOnClickListener {
            startActivity(sendIntent)
        }
    }
}