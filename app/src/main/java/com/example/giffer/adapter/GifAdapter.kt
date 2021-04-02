package com.example.giffer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.giffer.R
import com.example.giffer.model.Data
import kotlinx.android.synthetic.main.gif_item.view.*

class GifAdapter(private val onClick: (Data) -> Unit) :
    RecyclerView.Adapter<GifAdapter.GifViewHolder>() {

    private var gifList = emptyList<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.gif_item,
                parent,
                false
            )
        return GifViewHolder(view)
    }

    override fun getItemCount(): Int {
        return gifList.size
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        setGifToHolder(holder, position)
        val currentGif = gifList[position]
        holder.itemView.gif_element.setOnClickListener {
            onClick(currentGif)
        }
    }

    private fun setGifToHolder(
        holder: GifViewHolder,
        position: Int
    ) {
        val circularProgressDrawable = CircularProgressDrawable(holder.itemView.gif_element.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        Glide
            .with(holder.itemView.gif_element.context)
            .load(gifList[position].images.original.url)
            .placeholder(circularProgressDrawable)
            .into(holder.itemView.gif_element)
    }

    fun setData(gifList: List<Data>) {
        this.gifList = gifList
        notifyDataSetChanged()
    }

    class GifViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}
