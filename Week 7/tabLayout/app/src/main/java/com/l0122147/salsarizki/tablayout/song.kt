package com.l0122147.salsarizki.tablayout

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Song(
    val name: String,
    val description: String,
    val imageResId: Int,
    val spotifyTrackId: String, // Properti untuk menyimpan username Instagram
) : Parcelable