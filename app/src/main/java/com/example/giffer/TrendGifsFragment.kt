package com.example.giffer

import android.content.Intent
import android.graphics.drawable.AnimatedImageDrawable
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.giffer.adapter.GifAdapter
import com.example.giffer.repository.Repository
import com.example.giffer.util.Constants
import kotlinx.android.synthetic.main.fragment_trend_gifs.view.*
import kotlinx.android.synthetic.main.gif_item.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class TrendGifsFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    private val adapter by lazy {
        GifAdapter {
            val intent = Intent(this.context, GifActivity::class.java)
            intent.putExtra(Constants.CURRENT_GIF_URL, it.images.original.url)
            intent.putExtra(Constants.CURRENT_GIF_TITLE, it.title)
            startActivity(intent)
        }
    }

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_trend_gifs, container, false)
        initRecyclerView(view)
        initTrendGifs()
        return view
    }

    private fun initRecyclerView(view: View) {
        recyclerView = view.recycler_view_trends
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    private fun initTrendGifs() {
        viewModel.getTrendGifs()
        viewModel.gifTrendResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.data?.let { adapter.setData(it) }
            }
        })
    }
}