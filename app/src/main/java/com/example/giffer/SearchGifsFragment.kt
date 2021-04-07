package com.example.giffer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.giffer.adapter.GifAdapter
import com.example.giffer.api.RetrofitInstance
import com.example.giffer.repository.Repository
import kotlinx.android.synthetic.main.fragment_search_gifs.*
import kotlinx.android.synthetic.main.fragment_search_gifs.view.*

class SearchGifsFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

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
        val repository = Repository(RetrofitInstance)
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_search_gifs, container, false)
        viewModel.initRecycler(adapter, view.recycler_view_search)
        initSearchGifs(view)
        return view
    }

    private fun initSearchGifs(view: View) {
        view.category_search.text = viewModel.category
        view.search_button.setOnClickListener {
            viewModel.getGifs(
                view.edit_text.text.toString(),
                adapter,
                requireContext(),
                view.edit_text
            )
            view.category_search.text = viewModel.category
        }
    }
}