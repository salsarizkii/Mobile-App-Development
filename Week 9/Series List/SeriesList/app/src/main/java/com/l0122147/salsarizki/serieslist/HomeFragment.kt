package com.l0122147.salsarizki.serieslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.l0122147.salsarizki.serieslist.data.response.UserListResponse
import com.l0122147.salsarizki.serieslist.data.retrofit.ApiConfig
import com.l0122147.salsarizki.serieslist.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSeries()

        binding.btnSeeMore.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_seriesActivity)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun getSeries() {
        ApiConfig.apiService().getMostPopular().enqueue(object : Callback<UserListResponse> {
            override fun onResponse(
                call: Call<UserListResponse>,
                response: Response<UserListResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.let {
                        bindData(it)
                    }
                }
            }

            override fun onFailure(call: Call<UserListResponse>, t: Throwable) {
                // Handle error
            }
        })
    }

    private fun bindData(userResponse: UserListResponse) {
        binding.apply {
            seriesName.text = userResponse.tvShows[0].name
            seriesStartDate.text = userResponse.tvShows[0].startDate
            seriesCountry.text = userResponse.tvShows[0].country
            seriesStatus.text = userResponse.tvShows[0].status
            Glide.with(requireContext())
                .load(userResponse.tvShows[0].imageThumbnailPath)
                .into(seriesImage)
        }
    }
}
