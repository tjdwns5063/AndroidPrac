package com.example.recyclerre

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerre.database.MyModel
import com.example.recyclerre.databinding.RecyclerItemViewBinding

class MyModelViewHolder private constructor(val binding: RecyclerItemViewBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(myModel: MyModel) {
        binding.model = myModel
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): MyModelViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RecyclerItemViewBinding.inflate(layoutInflater, parent, false)
            return MyModelViewHolder(binding)
        }
    }
}