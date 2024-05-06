package com.l0122147.salsarizki.tablayout

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ListSongAdapter(private val listSong: ArrayList<Song>) :
    RecyclerView.Adapter<ListSongAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_song_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_song_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_song_description)

        fun bind(song: Song) {
            imgPhoto.setImageResource(song.imageResId)
            tvName.text = song.name
            tvDescription.text = song.description

            itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(song)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.song_information, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentSong = listSong[position]

        // Set image, name, and description for the current song
        holder.imgPhoto.setImageResource(currentSong.imageResId)
        holder.tvName.text = currentSong.name
        holder.tvDescription.text = currentSong.description

        // Set click listener to open Instagram profile when the item is clicked
        holder.itemView.setOnClickListener {
            // Call openInstagramProfile with the Instagram username of the current song
            currentSong.spotifyTrackId?.let { username ->
                openSpotifySong(username, holder.itemView)
            }
        }
    }

    override fun getItemCount(): Int = listSong.size

    interface OnItemClickCallback {
        fun onItemClicked(data: Song)
    }

    fun openSpotifySong(spotifyLink: String, itemView: View) {
        // Create URI to open Spotify song using the Spotify link
        val spotifyUri = Uri.parse(spotifyLink)
        val spotifyIntent = Intent(Intent.ACTION_VIEW, spotifyUri)
        spotifyIntent.setPackage("com.spotify.music") // Use Spotify app if available

        try {
            // Try to open Spotify song using Spotify app
            itemView.context.startActivity(spotifyIntent)
        } catch (e: ActivityNotFoundException) {
            // If Spotify app is not installed, notify the user
            Toast.makeText(itemView.context, "Spotify app is not installed", Toast.LENGTH_SHORT).show()
        }
    }


}
