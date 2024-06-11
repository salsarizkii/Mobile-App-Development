package com.l0122147.salsarizki.myapplication.ui.home
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Starred(
    val name: String,
    val description: String,
) : Parcelable