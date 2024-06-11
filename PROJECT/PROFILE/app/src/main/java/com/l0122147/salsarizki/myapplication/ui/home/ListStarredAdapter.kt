package com.l0122147.salsarizki.myapplication.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.l0122147.salsarizki.myapplication.R

class ListStarredAdapter(private val listStarred: ArrayList<Starred>) :
    RecyclerView.Adapter<ListStarredAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)

        init {
            itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(listStarred[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentStarred = listStarred[position]

        // Set name and description for the current starred item
        holder.tvName.text = currentStarred.name
        holder.tvDescription.text = currentStarred.description
    }

    override fun getItemCount(): Int {
        return listStarred.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Starred)
    }
}
