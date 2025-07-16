package com.example.myweatherapp.presentation.content

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.myweatherapp.R
import com.example.myweatherapp.presentation.WeatherMainActivity
import com.example.myweatherapp.presentation.home.HomeViewModel
import com.example.myweatherapp.presentation.viewmodel.LocationViewModel

class ContentFragment : Fragment(R.layout.fragment_content) {
    private lateinit var locationViewModel: LocationViewModel
    private lateinit var homeViewModel: HomeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        locationViewModel = (activity as WeatherMainActivity).locationViewModel
        homeViewModel = (activity as WeatherMainActivity).homeViewModel
    }
}