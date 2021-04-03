package com.example.giffer

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giffer.model.MainGif
import com.example.giffer.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    val gifResponse: MutableLiveData<Response<MainGif>> = MutableLiveData()
    val gifTrendResponse: MutableLiveData<Response<MainGif>> = MutableLiveData()

    fun getGifs(query: String) {
        viewModelScope.launch {
            val response = repository.getGifs(query)
            gifResponse.value = response
        }
    }

    fun getTrendGifs() {
        viewModelScope.launch {
            val response = repository.getTrendGifs()
            gifTrendResponse.value = response
        }
    }
}