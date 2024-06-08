package com.l0122147.salsarizki.serieslist

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.l0122147.salsarizki.serieslist.data.response.UserListResponse
import com.l0122147.salsarizki.serieslist.data.response.UserResponse

class ListSeriesAdapter(private val data: UserListResponse) :
    RecyclerView.Adapter<ListSingerAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvStartDate: TextView = itemView.findViewById(R.id.tv_item_start_date)
        val tvCountry: TextView = itemView.findViewById(R.id.tv_item_country)
        val tvStatus: TextView = itemView.findViewById(R.id.tv_item_status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_information, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        Glide.with(holder.itemView.context)
            .load(data.tvShows[position].imageThumbnailPath)
            .into(holder.imgPhoto)
        holder.tvName.text = data.tvShows[position].name
        holder.tvStartDate.text = data.tvShows[position].startDate
        holder.tvCountry.text = data.tvShows[position].country
        holder.tvStatus.text = data.tvShows[position].status
    }


    override fun getItemCount(): Int = data.tvShows.size

    fun setData(newData: UserListResponse) {
        data.tvShows.clear()
        data.tvShows.addAll(newData.tvShows)
        notifyDataSetChanged()
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Singer)
    }

    }