package com.example.recyclerprac

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.recyclerprac.database.MyModel

class MyRecyclerAdapter(val clickListener: MyModelListener) : ListAdapter<MyModel, MyViewHolder>(MyModelDiffCallBack()) {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }
}

class MyModelDiffCallBack : DiffUtil.ItemCallback<MyModel>() {
    override fun areContentsTheSame(oldItem: MyModel, newItem: MyModel): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: MyModel, newItem: MyModel): Boolean {
        return oldItem.nameId == newItem.nameId
    }
}

class MyModelListener(val clickListener: (nameId: Int) -> Unit) {
    fun onClick(model: MyModel) = clickListener(model.nameId)
}