package com.example.giffer

import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_gif.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GifActivity : AppCompatActivity() {

    private val viewModel: GifViewModel by viewModel()
    private lateinit var clipboard: ClipboardManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gif)
        viewModel.initGif(intent, this, gif_main, gif_title)
        initButtons()
    }

    private fun initButtons() {
        share_btn.setOnClickListener {
            startActivity(viewModel.shareIntent)
        }
        clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        copy_btn.setOnClickListener {
            viewModel.initCopyButton(this, clipboard)
        }
    }
}