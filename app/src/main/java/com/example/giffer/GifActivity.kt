package com.example.giffer

import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.giffer.databinding.ActivityGifBinding
import kotlinx.android.synthetic.main.activity_gif.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GifActivity : AppCompatActivity() {

    private val viewModel: GifViewModel by viewModel()

    private lateinit var clipboard: ClipboardManager
    private lateinit var binding: ActivityGifBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGifBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.initGif(intent)
        binding.gif = viewModel.currentGif
        binding.title = viewModel.currentGifTitle
        binding.executePendingBindings()
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