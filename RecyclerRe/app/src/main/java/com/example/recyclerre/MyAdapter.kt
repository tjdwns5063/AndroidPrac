package com.example.recyclerre

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerre.database.MyModel

class MyAdapter(): ListAdapter<MyModel, MyModelViewHolder>(MyModelDiffCallback()) {
    override fun onBindViewHolder(holder: MyModelViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyModelViewHolder {
        Log.i("ViewHolder", "onCreateViewHolder Called")
        return (MyModelViewHolder.from(parent))
    }
}

class MyModelDiffCallback: DiffUtil.ItemCallback<MyModel>() {
    override fun areContentsTheSame(oldItem: MyModel, newItem: MyModel): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: MyModel, newItem: MyModel): Boolean {
        return oldItem.nameId == newItem.nameId
    }
}