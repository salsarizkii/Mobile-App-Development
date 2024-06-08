package com.l0122147.salsarizki.serieslist.data.response

import com.google.gson.annotations.SerializedName

data class UserListResponse(
    @field:SerializedName("tv_shows")
    val tvShows: ArrayList<UserResponse>
)
// Data class untuk menampung response dari API terkait informasi series
data class UserResponse (
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("start_date")
    val startDate: String,

    @field:SerializedName("country")
    val country: String,

    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("image_thumbnail_path")
    val imageThumbnailPath: String,

)