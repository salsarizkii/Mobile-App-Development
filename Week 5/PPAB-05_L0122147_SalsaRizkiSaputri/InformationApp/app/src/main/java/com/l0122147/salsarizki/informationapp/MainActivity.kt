package com.l0122147.salsarizki.informationapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvSingers: RecyclerView
    private val list = ArrayList<Singer>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvSingers = findViewById(R.id.rv_Singer)
        rvSingers.setHasFixedSize(true)

        list.addAll(getListSingers())
        showRecyclerList()
    }

    private fun getListSingers(): ArrayList<Singer> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDesc = resources.getStringArray(R.array.data_desc)
        val dataImg = resources.obtainTypedArray(R.array.data_img)
        val dataInstagram = resources.getStringArray(R.array.data_instagram)

        val listSingers = ArrayList<Singer>()
        for (i in dataName.indices) {
            val Singer = Singer(
                dataName[i],
                dataDesc[i],
                dataImg.getResourceId(i, -1),
                dataInstagram[i] // Menggunakan nilai dataInstagram[i] sebagai instagramUsername
            )
            listSingers.add(Singer)
        }

        dataImg.recycle() // Pastikan untuk mendaur ulang TypedArray setelah digunakan
        return listSingers
    }

    private fun showRecyclerList() {
        rvSingers.layoutManager = LinearLayoutManager(this)
        val listSingerAdapter = ListSingerAdapter(list)
        rvSingers.adapter = listSingerAdapter

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
                rvSingers.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvSingers.layoutManager = GridLayoutManager(this, 2)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}