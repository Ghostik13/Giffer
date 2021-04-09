package com.example.giffer.app.main.presentation

import android.content.Context
import android.content.Intent
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.giffer.app.main.data.model.Data
import com.example.giffer.app.main.data.model.GifRemote
import com.example.giffer.app.main.domain.GifRepository
import com.example.giffer.app.main.presentation.detail.GifDetailActivity
import com.example.giffer.util.Constants
import kotlinx.coroutines.launch

class MainViewModel(private val repository: GifRepository) : ViewModel() {

    private val _gifResponse: MutableLiveData<GifRemote> = MutableLiveData()
    private val _gifTrendResponse: MutableLiveData<GifRemote> = MutableLiveData()

    val gifResponse: LiveData<GifRemote> = _gifResponse
    val gifTrendResponse: LiveData<GifRemote> = _gifTrendResponse

    private lateinit var recyclerView: RecyclerView

    val noGifs = "Sorry, no gifs found..."
    var category = ""

    fun getGifs(query: String, et: EditText) {
        viewModelScope.launch {
            val main = repository.loadGifs(query)
            _gifResponse.value = main
        }
        category = et.text.toString()
    }

    fun getTrendGifs() {
        viewModelScope.launch {
            val main = repository.loadTrendGifs()
            _gifTrendResponse.value = main
        }
    }

    fun initIntent(context: Context, gif: Data): Intent {
        val intent = Intent(context, GifDetailActivity::class.java)
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