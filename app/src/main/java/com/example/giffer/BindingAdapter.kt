package com.example.giffer

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide

@BindingAdapter("loadGifFromUrl")
fun ImageView.loadGifFromUrl(gif: String) {
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

@BindingAdapter("loadCurrentGif")
fun ImageView.loadCurrentGif(gif: String) {
    Glide
        .with(this)
        .load(gif)
        .into(this)
}

@BindingAdapter("setTitle")
fun TextView.setTitle(title: String) {
    this.text = title
}

@BindingAdapter("setCategory")
fun TextView.setCategory(category: String) {
    this.text = category
}