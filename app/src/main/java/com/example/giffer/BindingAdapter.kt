package com.example.giffer

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide

@BindingAdapter("loadGifFromUrl")
fun ImageView.loadGifFromUrl(gif: String){
    val circularProgressDrawable = CircularProgressDrawable(this.context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()
    Glide
        .with(this)
        .load(gif)
        .placeholder(circularProgressDrawable)
        .into(this)
}