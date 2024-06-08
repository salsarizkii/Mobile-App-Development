package com.l0122147.salsarizki.serieslist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Singer(
    val name: String,
    val country: String,
    val startDate: String,
    val status: String,
    val imageResId: Int,
    val instagramUsername: String, // Properti untuk menyimpan username Instagram
) : Parcelable