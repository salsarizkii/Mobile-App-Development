package com.l0122147.salsarizki.myapplication.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.l0122147.salsarizki.myapplication.R
import com.l0122147.salsarizki.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.btnStarred.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_navigation_home_to_starredFragment)
        )
        binding.btnBookmark.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_navigation_home_to_bookmarkFragment)
        )

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
