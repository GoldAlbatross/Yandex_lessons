package com.example.sprint16_architecture.ui.movies.activity

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import com.bumptech.glide.Glide
import androidx.recyclerview.widget.RecyclerView
import com.example.sprint16_architecture.R
import com.example.sprint16_architecture.domain.models.Movie


class MovieViewHolder(
    parent: ViewGroup,
    private val clickListener: MoviesAdapter.MovieClickListener,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false)
) {

    var cover: ImageView = itemView.findViewById(R.id.img_cover)
    var title: TextView = itemView.findViewById(R.id.title)
    var description: TextView = itemView.findViewById(R.id.description)
    // 1
    var inFavoriteToggle: ImageView = itemView.findViewById(R.id.favorite)

    fun bind(movie: Movie) {
        Glide.with(itemView)
            .load(movie.image)
            .into(cover)

        title.text = movie.title
        description.text = movie.description
        inFavoriteToggle.setImageDrawable(getFavoriteToggleDrawable(movie.inFavorite))

        itemView.setOnClickListener { clickListener.onMovieClick(movie) }
        inFavoriteToggle.setOnClickListener { clickListener.onFavoriteToggleClick(movie) }
    }

    private fun getFavoriteToggleDrawable(inFavorite: Boolean): Drawable? {
        return AppCompatResources.getDrawable(itemView.context,
            if (inFavorite) R.drawable.inactive_favorire else R.drawable.active_favorire
        )
    }
}