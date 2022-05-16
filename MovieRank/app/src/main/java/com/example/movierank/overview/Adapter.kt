package com.example.movierank.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movierank.databinding.RecyclerItemViewBinding
import com.example.movierank.network.MovieInfo

class Adapter: ListAdapter<MovieInfo, Adapter.ViewHolder>(DiffCallback()) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return (ViewHolder.from(parent))
    }

    class ViewHolder(val binding: RecyclerItemViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieInfo) {
            val text = item.rnum + ". " + item.movieNm
            binding.recyclerText.text = text
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecyclerItemViewBinding.inflate(layoutInflater, parent, false)
                return (ViewHolder(binding))
            }
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<MovieInfo>() {
        override fun areContentsTheSame(oldItem: MovieInfo, newItem: MovieInfo): Boolean {
            return (oldItem.rnum == newItem.rnum)
        }

        override fun areItemsTheSame(oldItem: MovieInfo, newItem: MovieInfo): Boolean {
            return (oldItem == newItem)
        }
    }
}