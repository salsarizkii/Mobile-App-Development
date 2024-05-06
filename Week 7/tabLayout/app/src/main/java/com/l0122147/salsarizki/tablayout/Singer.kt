package com.l0122147.salsarizki.tablayout

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Singer(
    val name: String,
    val description: String,
    val imageResId: Int,
    val instagramUsername: String, // Properti untuk menyimpan username Instagram
) : Parcelable