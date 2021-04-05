package com.example.giffer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.giffer.adapter.GifAdapter
import com.example.giffer.repository.Repository
import kotlinx.android.synthetic.main.fragment_trend_gifs.view.*
import retrofit2.Response

class TrendGifsFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView

    private val adapter by lazy {
        GifAdapter {
            val intent = viewModel.initIntent(requireContext(), it)
            startActivity(intent)
        }
    }

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