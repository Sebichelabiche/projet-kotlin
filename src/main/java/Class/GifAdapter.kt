package com.example.giphy2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giphy2.databinding.ItemGifBinding
import com.squareup.picasso.Picasso
import dataclass.Gif
class GifAdapter : RecyclerView.Adapter<GifAdapter.GifViewHolder>() {

    private val gifs = mutableListOf<Gif>()

    fun updateData(newGifs: List<Gif>) {
        gifs.clear()
        gifs.addAll(newGifs)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gif, parent, false)
        return GifViewHolder(view)
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        holder.bind(gifs[position])
    }

    override fun getItemCount(): Int = gifs.size

    inner class GifViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val gifImageView: ImageView = itemView.findViewById(R.id.ivGif)

        fun bind(gif: Gif) {
            Glide.with(itemView.context)
                .load(gif.url)
                .placeholder(R.drawable.wait)
                .into(gifImageView)
        }
    }
}


