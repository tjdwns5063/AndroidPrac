package com.example.networkprac2.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.networkprac2.databinding.RecyclerItemViewBinding
import com.example.networkprac2.network.DailyBoxOfficeList

class MovieAdapter(private val onClickListener: OnClickListener): ListAdapter<DailyBoxOfficeList, MovieAdapter.MovieViewHolder>(DiffCallback()) {
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(RecyclerItemViewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    class MovieViewHolder(private val binding: RecyclerItemViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(dailyBoxOfficeList: DailyBoxOfficeList) {
            binding.dailyBoxOffice = dailyBoxOfficeList
            binding.executePendingBindings()
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<DailyBoxOfficeList>() {
        override fun areContentsTheSame(
            oldItem: DailyBoxOfficeList,
            newItem: DailyBoxOfficeList
        ): Boolean {
            return oldItem.movieNm == newItem.movieNm
        }

        override fun areItemsTheSame(
            oldItem: DailyBoxOfficeList,
            newItem: DailyBoxOfficeList
        ): Boolean {
            return oldItem == newItem
        }
    }

    class OnClickListener(val clickListener: (movieProperty: DailyBoxOfficeList) -> Unit) {
        fun onClick(movieProperty: DailyBoxOfficeList) = clickListener(movieProperty)
    }
}