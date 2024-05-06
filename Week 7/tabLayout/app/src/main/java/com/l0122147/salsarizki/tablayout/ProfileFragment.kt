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

class ProfileFragment : Fragment() {

    private lateinit var rvSongs: RecyclerView
    private val list = ArrayList<Song>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvSongs = view.findViewById(R.id.rv_song)
        rvSongs.setHasFixedSize(true)

        list.addAll(getListSongs())
        showRecyclerList()
    }

    private fun getListSongs(): ArrayList<Song> {
        val dataName = resources.getStringArray(R.array.song_name)
        val dataDesc = resources.getStringArray(R.array.song_desc)
        val dataImg = resources.obtainTypedArray(R.array.song_img)
        val dataInstagram = resources.getStringArray(R.array.song_spotify)

        val listSongs = ArrayList<Song>()
        for (i in dataName.indices) {
            val song = Song(
                dataName[i],
                dataDesc[i],
                dataImg.getResourceId(i, -1),
                dataInstagram[i] // Using dataInstagram[i] value as instagramUsername
            )
            listSongs.add(song)
        }

        dataImg.recycle() // Make sure to recycle TypedArray after use
        return listSongs
    }

    private fun showRecyclerList() {
        rvSongs.layoutManager = LinearLayoutManager(requireContext())
        val listSongAdapter = ListSongAdapter(list)
        rvSongs.adapter = listSongAdapter

        listSongAdapter.setOnItemClickCallback(object : ListSongAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Song) {
                showSelectedSong(data)
            }

        })
    }

    private fun showSelectedSong(song: Song) {
        Toast.makeText(requireContext(), "${song.name} is selected", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_option, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvSongs.layoutManager = LinearLayoutManager(requireContext())
            }
            R.id.action_grid -> {
                rvSongs.layoutManager = GridLayoutManager(requireContext(), 2)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
