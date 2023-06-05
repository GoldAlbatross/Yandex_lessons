package com.example.sprint16_architecture.ui.cast

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sprint16_architecture.R

class MoviesCastAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var listItem = emptyList<MovieCastRVItem>()

    // Возвращаем нужный ViewType в зависимости от типа элементов списка
    override fun getItemViewType(position: Int): Int {
        return when (listItem[position]) {
            is MovieCastRVItem.HeaderItem -> R.layout.list_item_header
            is MovieCastRVItem.PersonItem -> R.layout.list_item_cast
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return  when (viewType) {
            R.layout.list_item_header -> MovieCastHeaderViewHolder(parent)
            R.layout.list_item_cast -> MovieCastViewHolder(parent)
            else -> error("Unknown viewType create [$viewType]")
        }

    }

    // Биндим ViewHolder корректно, в зависимости от viewType
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            R.layout.list_item_header -> {
                val headerHolder = holder as MovieCastHeaderViewHolder
                headerHolder.bind(listItem[position] as MovieCastRVItem.HeaderItem)
            }
            R.layout.list_item_cast -> {
                val headerHolder = holder as MovieCastViewHolder
                headerHolder.bind(listItem[position] as MovieCastRVItem.PersonItem)
            }
            else -> error("Unknown viewType bind [${holder.itemViewType}]")
        }
    }

    override fun getItemCount(): Int = listItem.size
}


class MovieCastViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item_cast, parent, false)
    ) {

    private val actorImage: ImageView = itemView.findViewById(R.id.actorImageView)
    private val personName: TextView = itemView.findViewById(R.id.actorNameTextView)
    private val personDescription: TextView = itemView.findViewById(R.id.actorDescriptionTextView)

    fun bind(item: MovieCastRVItem.PersonItem) {
        if (item.data.image == null) {
            actorImage.isVisible = false
        } else {
            Glide.with(itemView)
                .load(item.data.image)
                .into(actorImage)
            actorImage.isVisible = true
        }

        personName.text = item.data.name
        personDescription.text = item.data.description
    }
}


class MovieCastHeaderViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item_header, parent, false)
) {

    var headerTextView: TextView = itemView.findViewById(R.id.headerTextView)

    fun bind(item: MovieCastRVItem.HeaderItem) {
        headerTextView.text = item.headerText
    }

}