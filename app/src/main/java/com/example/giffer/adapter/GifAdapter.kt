package com.example.giffer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.giffer.databinding.GifItemBinding
import com.example.giffer.model.Data
import kotlinx.android.synthetic.main.gif_item.view.*

class GifAdapter(private val onClick: (Data) -> Unit) :
    RecyclerView.Adapter<GifAdapter.GifViewHolder>() {

    private var gifList = emptyList<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = GifItemBinding.inflate(layoutInflater, parent, false)
        return GifViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return gifList.size
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        val currentGif = gifList[position]
        holder.binding.gif = currentGif.images.original
        holder.binding.executePendingBindings()
        holder.itemView.gif_element.setOnClickListener {
            onClick(currentGif)
        }
    }

    fun setData(gifList: List<Data>) {
        this.gifList = gifList
        notifyDataSetChanged()
    }

    class GifViewHolder(var binding: GifItemBinding) : RecyclerView.ViewHolder(binding.root)
}
