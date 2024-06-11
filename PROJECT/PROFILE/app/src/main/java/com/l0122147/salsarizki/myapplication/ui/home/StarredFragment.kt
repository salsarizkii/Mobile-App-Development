package com.l0122147.salsarizki.myapplication.ui.home

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.l0122147.salsarizki.myapplication.R

class StarredFragment() : Fragment() {

    private lateinit var rvStarreds: RecyclerView
    private val list = ArrayList<Starred>()

    constructor(parcel: Parcel) : this() {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_starred, container, false)

        view.apply {
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            rvStarreds = findViewById(R.id.rv_Starred)
            rvStarreds.setHasFixedSize(true)
        }

        list.addAll(getListStarreds())
        showRecyclerList()

        return view
    }

    private fun getListStarreds(): ArrayList<Starred> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDesc = resources.getStringArray(R.array.data_desc)

        val listStarreds = ArrayList<Starred>()
        for (i in dataName.indices) {
            val starred = Starred(
                dataName[i],
                dataDesc[i]
            )
            listStarreds.add(starred)
        }

        return listStarreds
    }

    private fun showRecyclerList() {
        rvStarreds.layoutManager = LinearLayoutManager(requireContext())
        val listStarredAdapter = ListStarredAdapter(list)
        rvStarreds.adapter = listStarredAdapter

        listStarredAdapter.setOnItemClickCallback(object : ListStarredAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Starred) {
                showSelectedStarred(data)
            }
        })
    }

    companion object CREATOR : Parcelable.Creator<StarredFragment> {
        override fun createFromParcel(parcel: Parcel): StarredFragment {
            return StarredFragment(parcel)
        }

        override fun newArray(size: Int): Array<StarredFragment?> {
            return arrayOfNulls(size)
        }
    }

    private fun showSelectedStarred(starred: Starred) {
        Toast.makeText(requireContext(), starred.name + " is selected", Toast.LENGTH_SHORT).show()
    }
}
