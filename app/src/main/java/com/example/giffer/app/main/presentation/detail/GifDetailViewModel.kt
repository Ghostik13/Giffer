package com.example.giffer.app.main.presentation.detail

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.giffer.R
import com.example.giffer.util.Constants

class GifDetailViewModel : ViewModel() {

    var currentGif: String = ""
    var currentGifTitle: String = ""

    val shareIntent = shareIntent()

    fun initGif(intent: Intent) {
        val bundle = intent.extras
        if (bundle != null) {
            currentGif = bundle.getString(Constants.CURRENT_GIF_URL)!!
            currentGifTitle = bundle.getString(Constants.CURRENT_GIF_TITLE)!!
        }
    }

    private fun shareIntent(): Intent {
        val sendIntent = Intent(Intent.ACTION_SEND)
        sendIntent.putExtra(Intent.EXTRA_TEXT, currentGif)
        sendIntent.type = "text/plain"
        return sendIntent
    }

    fun initCopyButton(context: Context, clipboard: ClipboardManager) {
        val clip = ClipData.newPlainText("CopiedText", currentGif)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(
            context,
            R.string.copy,
            Toast.LENGTH_SHORT
        ).show()
    }
}