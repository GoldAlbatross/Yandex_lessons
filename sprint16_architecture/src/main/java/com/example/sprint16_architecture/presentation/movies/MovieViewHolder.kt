package com.example.sprint16_architecture.presentation.movies

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import androidx.recyclerview.widget.RecyclerView
import com.example.sprint16_architecture.R
import com.example.sprint16_architecture.domain.models.Movie

class MovieViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item_movie, parent, false)
) {

    private var cover: ImageView = itemView.findViewById(R.id.img_cover)
    private var title: TextView = itemView.findViewById(R.id.title)
    private var description: TextView = itemView.findViewById(R.id.description)

    fun bind(movie: Movie) {
        Glide
            .with(itemView)
            .load(movie.image)
            .into(cover)

        title.text = movie.title
        description.text = movie.description
    }
}