package com.l0122147.salsarizki.serieslist

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.l0122147.salsarizki.serieslist.data.response.UserListResponse
import com.l0122147.salsarizki.serieslist.data.response.UserResponse
import com.l0122147.salsarizki.serieslist.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SeriesActivity : AppCompatActivity() {

    private lateinit var rvSeries: RecyclerView
    private lateinit var adapter: ListSingerAdapter
//    private val list = ArrayList<Singer>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_series)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        adapter = ListSingerAdapter(UserListResponse(ArrayList<UserResponse>()))

        rvSeries = findViewById(R.id.rv_Series)
        rvSeries.setHasFixedSize(true)

//        list.addAll(getListSingers())
        getSeries()
        showRecyclerList()
    }

    fun getSeries() {
        ApiConfig.apiService().getMostPopular().enqueue(object : Callback<UserListResponse> {
            override fun onResponse(
                call: Call<UserListResponse>,
                response: Response<UserListResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.let {
                        setData(it)
                    }
                }
            }

            override fun onFailure(call: Call<UserListResponse>, t: Throwable) {
                Log.d("Error", "" + t.stackTraceToString())
            }
        })
    }

    fun setData(data: UserListResponse) {
        adapter.setData(data)
    }


//    private fun getListSingers(): ArrayList<Singer> {
//        val dataName = resources.getStringArray(R.array.data_name)
//        val dataDesc = resources.getStringArray(R.array.data_desc)
//        val dataImg = resources.obtainTypedArray(R.array.data_img)
//        val dataInstagram = resources.getStringArray(R.array.data_instagram)
//
//        val listSingers = ArrayList<Singer>()
//        for (i in dataName.indices) {
//            val Singer = Singer(
//                dataName[i],
//                dataDesc[i],
//                dataImg.getResourceId(i, -1),
//                dataInstagram[i] // Menggunakan nilai dataInstagram[i] sebagai instagramUsername
//            )
//            listSingers.add(Singer)
//        }
//
//        dataImg.recycle() // Pastikan untuk mendaur ulang TypedArray setelah digunakan
//        return listSingers
//    }




    private fun showRecyclerList() {
        rvSeries.layoutManager = LinearLayoutManager(this)
        val listSingerAdapter = adapter
        rvSeries.adapter = listSingerAdapter

        listSingerAdapter.setOnItemClickCallback(object : ListSingerAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Singer) {
                showSelectedSinger(data)
            }

        })
    }

    private fun showSelectedSinger(Singer: Singer) {
        Toast.makeText(this, Singer.name + " is selected", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvSeries.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvSeries.layoutManager = GridLayoutManager(this, 2)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}