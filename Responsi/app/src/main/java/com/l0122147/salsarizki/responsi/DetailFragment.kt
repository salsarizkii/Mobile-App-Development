package com.l0122147.salsarizki.responsi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.l0122147.salsarizki.responsi.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataImage = arguments?.getInt("image")
        val dataName = arguments?.getString("name")
        val dataDescription = arguments?.getString("description")

        if (dataImage != null) {
            binding.imgItemPhoto.setImageResource(dataImage)
        }
        binding.tvItemName.text = dataName
        binding.tvItemDescription.text = dataDescription
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}