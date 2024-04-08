package com.l0122147.salsarizki.informationapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListCatAdapter(private val listCat: ArrayList<Cat>) :
    RecyclerView.Adapter<ListCatAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)

        fun bind(cat: Cat) {
            imgPhoto.setImageResource(cat.imageResId)
            tvName.text = cat.name
            tvDescription.text = cat.description

            itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(cat)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_information, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentCat = listCat[position]

        // Set image, name, and description for the current cat
        holder.imgPhoto.setImageResource(currentCat.imageResId)
        holder.tvName.text = currentCat.name
        holder.tvDescription.text = currentCat.description

        // Set click listener to open Instagram profile when the item is clicked
        holder.itemView.setOnClickListener {
            // Call openInstagramProfile with the Instagram username of the current cat
            currentCat.instagramUsername?.let { username ->
                openInstagramProfile(username, holder.itemView)
            }
        }
    }


    override fun getItemCount(): Int = listCat.size

    interface OnItemClickCallback {
        fun onItemClicked(data: Cat)
    }

    fun openInstagramProfile(username: String, itemView: View) {
        // Membuat URI untuk membuka profil Instagram menggunakan username
        val instagramUri = Uri.parse("http://instagram.com/_u/$username")
        val instagramIntent = Intent(Intent.ACTION_VIEW, instagramUri)
        instagramIntent.setPackage("com.instagram.android") // Menggunakan Instagram app jika tersedia

        try {
            // Mencoba membuka profil Instagram menggunakan aplikasi Instagram
            itemView.context.startActivity(instagramIntent)
        } catch (e: ActivityNotFoundException) {
            // Jika aplikasi Instagram tidak terpasang, buka melalui browser
            instagramIntent.setPackage(null)
            itemView.context.startActivity(instagramIntent)
        }
    }
    }