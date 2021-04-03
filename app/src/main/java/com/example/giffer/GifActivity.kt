package com.example.giffer

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.giffer.util.Constants.Companion.CURRENT_GIF_TITLE
import com.example.giffer.util.Constants.Companion.CURRENT_GIF_URL
import kotlinx.android.synthetic.main.activity_gif.*

class GifActivity : AppCompatActivity() {

    private var currentGif: String? = null
    private var currentGifTitle: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gif)
        initGif()
        initButtons()
    }

    private fun initGif() {
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
    }

    private fun initButtons() {
        val sendIntent = Intent(Intent.ACTION_SEND)
        sendIntent.putExtra(Intent.EXTRA_TEXT, currentGif.toString())
        sendIntent.type = "text/plain"

        share_btn.setOnClickListener {
            startActivity(sendIntent)
        }

        copy_btn.setOnClickListener {
            val clipboard: ClipboardManager =
                getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("CopiedText", currentGif.toString())
            clipboard.setPrimaryClip(clip)
            Toast.makeText(
                this,
                R.string.copy,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}