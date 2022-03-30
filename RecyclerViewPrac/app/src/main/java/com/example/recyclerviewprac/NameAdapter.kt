package com.example.recyclerviewprac

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewprac.database.NameEntity

class NameAdapter(val context: Context, var nameList: List<NameEntity>): RecyclerView.Adapter<NameAdapter.TextItemViewHolder>() {

    override fun getItemCount() = nameList.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = nameList[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.text_item_view, parent, false) as TextView
        return TextItemViewHolder(view)
    }

    class TextItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.findViewById(R.id.name_text_view)
        fun bind(item: NameEntity) {
            txtName.text = item.name
        }
    }

}