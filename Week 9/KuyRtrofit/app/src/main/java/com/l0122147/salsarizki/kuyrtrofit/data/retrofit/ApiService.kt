package com.l0122147.salsarizki.kuyrtrofit.data.retrofit
import com.l0122147.salsarizki.kuyrtrofit.data.response.UserResponse
import retrofit2.Call
import retrofit2.http.*

// Interface untuk menentukan endpoint API
interface ApiService {
    // Mendefinisikan endpoint untuk mendapatkan daftar user
    @GET("users")
    fun getUsers(): Call<ArrayList<UserResponse>>
}