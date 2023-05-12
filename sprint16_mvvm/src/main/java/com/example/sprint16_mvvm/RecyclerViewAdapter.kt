package com.example.sprint16_mvvm


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sprint16_mvvm.databinding.RecyclerItemBinding

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        return ViewHolder(RecyclerItemBinding.inflate(layoutInspector, parent, false))    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


    class ViewHolder(private val binding: RecyclerItemBinding): RecyclerView.ViewHolder(binding.root) {

//        fun bind(item: Item) {
//            binding.title.text = item.text
//            binding.field.text = item.field
//        }
    }
}