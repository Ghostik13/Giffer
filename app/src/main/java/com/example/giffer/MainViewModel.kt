package com.example.giffer

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.giffer.adapter.GifAdapter
import com.example.giffer.model.Data
import com.example.giffer.model.MainGif
import com.example.giffer.repository.Repository
import com.example.giffer.util.Constants
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val _gifResponse: MutableLiveData<Response<MainGif>> = MutableLiveData()
    private val _gifTrendResponse: MutableLiveData<Response<MainGif>> = MutableLiveData()

    private val gifResponse: LiveData<Response<MainGif>> = _gifResponse
    private val gifTrendResponse: LiveData<Response<MainGif>> = _gifTrendResponse

    private val noGifs = "Sorry, no gifs found..."

    private lateinit var recyclerView: RecyclerView

    fun getGifs(
        query: String,
        adapter: GifAdapter,
        context: Context,
        et: EditText,
        tv: TextView
    ) {
        viewModelScope.launch {
            val response = repository.getGifs(query)
            _gifResponse.value = response
        }
        tv.text = et.text.toString()
        tv.visibility = View.VISIBLE
        gifResponse.observeForever { response ->
            if (response.isSuccessful) {
                response.body()?.data?.let { adapter.setData(it) }
                if (response.body()!!.data.isEmpty()) {
                    tv.text = noGifs
                }
            } else {
                Toast.makeText(context, response.code(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getTrendGifs(adapter: GifAdapter) {
        viewModelScope.launch {
            val response = repository.getTrendGifs()
            _gifTrendResponse.value = response
        }
        gifTrendResponse.observeForever { response ->
            if (response.isSuccessful) {
                response.body()?.data?.let { adapter.setData(it) }
            }
        }
    }

    fun initIntent(context: Context, gif: Data): Intent {
        val intent = Intent(context, GifActivity::class.java)
        intent.putExtra(Constants.CURRENT_GIF_URL, gif.images.original.url)
        intent.putExtra(Constants.CURRENT_GIF_TITLE, gif.title)
        return intent
    }

    fun initRecycler(adapter: GifAdapter, rv: RecyclerView) {
        recyclerView = rv
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }
}