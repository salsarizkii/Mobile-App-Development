package com.l0122147.salsarizki.informationapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cat(
    val name: String,
    val description: String,
    val imageResId: Int,
    val instagramUsername: String, // Properti untuk menyimpan username Instagram
) : Parcelable