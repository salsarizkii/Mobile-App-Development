package com.l0122147.salsarizki.kuyrtrofit.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.l0122147.salsarizki.kuyrtrofit.data.response.UserResponse
import com.l0122147.salsarizki.kuyrtrofit.data.retrofit.ApiConfig
import com.l0122147.salsarizki.kuyrtrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding // Declare binding as lateinit var

    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater) // Initialize binding correctly

        setContentView(binding.root)

        adapter = UserAdapter(this@MainActivity, arrayListOf())

        binding.rvUser.adapter = adapter
        binding.rvUser.setHasFixedSize(true)
        binding.rvUser.layoutManager = LinearLayoutManager(this)

        getUsers()
    }

    private fun getUsers() {
        ApiConfig.apiService().getUsers().enqueue(object : Callback<ArrayList<UserResponse>> {
            override fun onResponse(
                call: Call<ArrayList<UserResponse>>,
                response: Response<ArrayList<UserResponse>>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.let {
                        setData(it)
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<UserResponse>>, t: Throwable) {
                Log.d("Error", "" + t.stackTraceToString())
            }
        })
    }

    private fun setData(data: ArrayList<UserResponse>) {
        adapter.setData(data)
    }
}
