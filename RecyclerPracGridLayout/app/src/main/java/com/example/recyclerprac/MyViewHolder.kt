package com.example.recyclerprac

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerprac.database.MyModel
import com.example.recyclerprac.databinding.RecyclerItemViewBinding

class MyViewHolder private constructor(val binding: RecyclerItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(myModel: MyModel, clickListener: MyModelListener) {
        binding.model = myModel
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): MyViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RecyclerItemViewBinding.inflate(layoutInflater, parent, false)
            return MyViewHolder(binding)
        }
    }
}