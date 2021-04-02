package com.example.giffer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.giffer.adapter.GifAdapter
import com.example.giffer.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val adapter by lazy { GifAdapter() }
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        initTrendGifs()
        initSearchGifs()
    }

    private fun initRecyclerView() {
        recyclerView = recycler_view
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    private fun initSearchGifs() {
        search_btn.setOnClickListener {
            val category = edit_text.text.toString()
            viewModel.getGifs(category)
            viewModel.gifResponse.observe(this, Observer { response ->
                if (response.isSuccessful) {
                    response.body()?.data?.let { adapter.setData(it) }
                } else {
                    Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun initTrendGifs() {
        viewModel.getTrendGifs()
        viewModel.gifTrendResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.data?.let { adapter.setData(it) }
            } else {
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        })
    }
}