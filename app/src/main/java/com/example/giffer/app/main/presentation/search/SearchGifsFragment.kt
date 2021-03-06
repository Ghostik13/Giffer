package com.example.giffer.app.main.presentation.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.giffer.R
import com.example.giffer.app.main.presentation.GifAdapter
import com.example.giffer.app.main.presentation.MainViewModel
import com.example.giffer.databinding.FragmentSearchGifsBinding
import kotlinx.android.synthetic.main.fragment_search_gifs.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchGifsFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var binding: FragmentSearchGifsBinding

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
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_search_gifs, container, false
        )
        viewModel.initRecycler(adapter, binding.root.recycler_view_search)
        initSearchGifs(binding.root)
        return binding.root
    }

    private fun initSearchGifs(view: View) {
        binding.category = viewModel.category
        view.search_button.setOnClickListener {
            val query = view.edit_text.text.toString()
            val editText = view.edit_text
            viewModel.getGifs(query, editText)
            viewModel.gifResponse.observe(viewLifecycleOwner, Observer { main ->
                main.data.let { adapter.setData(it) }
                if (main.data.isEmpty()) {
                    view.category_search.text = viewModel.noGifs
                }
            })
            binding.category = viewModel.category
        }
    }
}