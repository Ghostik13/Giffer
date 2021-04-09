package com.example.giffer.app.main.presentation.trends

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.giffer.app.main.presentation.GifAdapter
import kotlinx.android.synthetic.main.fragment_trend_gifs.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer
import com.example.giffer.R
import com.example.giffer.app.main.presentation.MainViewModel
import com.example.giffer.databinding.FragmentTrendGifsBinding

class TrendGifsFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var binding: FragmentTrendGifsBinding

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
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_trend_gifs, container, false)
        viewModel.initRecycler(adapter, binding.root.recycler_view_trends)
        initTrendGifs()
        return binding.root
    }

    private fun initTrendGifs() {
        viewModel.getTrendGifs()
        viewModel.gifTrendResponse.observe(viewLifecycleOwner, Observer { main ->
            main.data.let { adapter.setData(it) }
        })
    }
}