package com.example.giffer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.giffer.adapter.GifAdapter
import kotlinx.android.synthetic.main.fragment_trend_gifs.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrendGifsFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()

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
        val view = inflater.inflate(R.layout.fragment_trend_gifs, container, false)
        viewModel.initRecycler(adapter, view.recycler_view_trends)
        viewModel.getTrendGifs(adapter)
        return view
    }
}