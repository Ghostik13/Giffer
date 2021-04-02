package com.example.giffer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giffer.MainActivity
import com.example.giffer.model.Data
import com.example.giffer.model.MainGif
import kotlinx.android.synthetic.main.gif_item.view.*

class GifAdapter: RecyclerView.Adapter<GifAdapter.GifViewHolder>(){

    private var gifList = emptyList<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(com.example.giffer.R.layout.gif_item, parent, false)
        return GifViewHolder(view)
    }

    override fun getItemCount(): Int {
        return gifList.size
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        Glide
            .with(holder.itemView.gif_element.context)
            .load(gifList[position].images.original.url)
            .into(holder.itemView.gif_element)
        holder.itemView.progress_bar.visibility = View.INVISIBLE
    }

    fun setData(gifList: List<Data>){
        this.gifList = gifList
        notifyDataSetChanged()
    }

    class GifViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

}
