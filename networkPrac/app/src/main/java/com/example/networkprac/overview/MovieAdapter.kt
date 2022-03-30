package com.example.networkprac.overview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.networkprac.R
import com.example.networkprac.databinding.RecyclerItemViewBinding
import com.example.networkprac.network.Boxoffice

class MovieAdapter( private val onclickListener: OnClickListener): ListAdapter<Boxoffice, MovieAdapter.MovieViewHolder>(DiffCallback) {

    class MovieViewHolder(private val binding: RecyclerItemViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(boxOffice: Boxoffice) {
            binding.boxoffice = boxOffice
            Log.i("checkName", "${boxOffice.movieNm}")
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (movieProperty: Boxoffice) -> Unit) {
        fun onClick(movieProperty: Boxoffice) = clickListener(movieProperty)
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Boxoffice>() {
        override fun areContentsTheSame(oldItem: Boxoffice, newItem: Boxoffice): Boolean {
            return oldItem.movieNm == newItem.movieNm
        }

        override fun areItemsTheSame(oldItem: Boxoffice, newItem: Boxoffice): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        Log.i("RecyclerView", "onCreateViewHolder called")
        return (MovieViewHolder(RecyclerItemViewBinding.inflate(LayoutInflater.from(parent.context))))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onclickListener.onClick(item)
        }
        Log.i("RecyclerView", "onBindViewHolder called")
    }
}