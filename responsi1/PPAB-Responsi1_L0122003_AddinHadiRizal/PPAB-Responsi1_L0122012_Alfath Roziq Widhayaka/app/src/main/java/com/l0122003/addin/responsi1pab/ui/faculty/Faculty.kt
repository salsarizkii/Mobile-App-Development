package com.l0122003.addin.responsi1pab.ui.faculty

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Faculty(
    val name: String,
    val desc: String,
    val img: Int
) : Parcelable