package com.example.movierank.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movierank.databinding.RecyclerItemViewBinding
import com.example.movierank.network.ResultData

class Adapter: ListAdapter<ResultData, Adapter.ViewHolder>(DiffCallback()) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return (ViewHolder.from(parent))
    }

    class ViewHolder(val binding: RecyclerItemViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResultData) {
            val text = item.movieInfo.rnum + ". " + item.movieInfo.movieNm
            binding.recyclerText.text = text
            Glide.with(binding.root)
                .load(item.image)
                .into(binding.recyclerImg)
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecyclerItemViewBinding.inflate(layoutInflater, parent, false)
                return (ViewHolder(binding))
            }
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<ResultData>() {
        override fun areContentsTheSame(oldItem: ResultData, newItem: ResultData): Boolean {
            return (oldItem.movieInfo.rnum == newItem.movieInfo.rnum)
        }

        override fun areItemsTheSame(oldItem: ResultData, newItem: ResultData): Boolean {
            return (oldItem == newItem)
        }
    }
}