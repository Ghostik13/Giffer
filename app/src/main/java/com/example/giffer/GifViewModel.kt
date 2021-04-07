package com.example.giffer

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.giffer.util.Constants
import kotlinx.android.synthetic.main.gif_item.view.*

class GifViewModel : ViewModel() {

    private var currentGif: String? = null
    private var currentGifTitle: String? = null

    val shareIntent = shareIntent()

    fun initGif(intent: Intent, context: Context, gif: ImageView, title: TextView) {
        val bundle = intent.extras
        if (bundle != null) {
            currentGif = bundle.getString(Constants.CURRENT_GIF_URL)
            currentGifTitle = bundle.getString(Constants.CURRENT_GIF_TITLE)
        }
        currentGif?.let {
            Glide
                .with(context)
                .load(it)
                .into(gif)
        }
        currentGifTitle?.let {
            title.text = it
        }
    }

    private fun shareIntent(): Intent {
        val sendIntent = Intent(Intent.ACTION_SEND)
        sendIntent.putExtra(Intent.EXTRA_TEXT, currentGif.toString())
        sendIntent.type = "text/plain"
        return sendIntent
    }

    fun initCopyButton(context: Context, clipboard: ClipboardManager) {

        val clip = ClipData.newPlainText("CopiedText", currentGif.toString())
        clipboard.setPrimaryClip(clip)
        Toast.makeText(
            context,
            R.string.copy,
            Toast.LENGTH_SHORT
        ).show()
    }
}