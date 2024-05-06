package com.l0122147.salsarizki.tablayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    private lateinit var rvSingers: RecyclerView
    private val list = ArrayList<Singer>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvSingers = view.findViewById(R.id.rv_Singer)
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
            val singer = Singer(
                dataName[i],
                dataDesc[i],
                dataImg.getResourceId(i, -1),
                dataInstagram[i] // Menggunakan nilai dataInstagram[i] sebagai instagramUsername
            )
            listSingers.add(singer)
        }

        dataImg.recycle() // Pastikan untuk mendaur ulang TypedArray setelah digunakan
        return listSingers
    }

    private fun showRecyclerList() {
        rvSingers.layoutManager = LinearLayoutManager(requireContext())
        val listSingerAdapter = ListSingerAdapter(list)
        rvSingers.adapter = listSingerAdapter

        listSingerAdapter.setOnItemClickCallback(object : ListSingerAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Singer) {
                showSelectedSinger(data)
            }

        })
    }

    private fun showSelectedSinger(singer: Singer) {
        Toast.makeText(requireContext(), singer.name + " is selected", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater?.inflate(R.menu.option_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvSingers.layoutManager = LinearLayoutManager(requireContext())
            }
            R.id.action_grid -> {
                rvSingers.layoutManager = GridLayoutManager(requireContext(), 2)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
