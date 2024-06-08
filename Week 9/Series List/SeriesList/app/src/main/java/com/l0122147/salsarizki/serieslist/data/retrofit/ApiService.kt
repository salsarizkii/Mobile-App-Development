package com.l0122147.salsarizki.serieslist.data.retrofit

import com.l0122147.salsarizki.serieslist.data.response.UserListResponse
import retrofit2.Call
import retrofit2.http.*

// Interface untuk menentukan endpoint API
interface ApiService {
    // Mendefinisikan endpoint untuk mendapatkan list series
    @GET("most-popular")
    fun getMostPopular(): Call<UserListResponse>
}