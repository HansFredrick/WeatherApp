package com.example.myweatherapp.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.myweatherapp.R
import com.example.myweatherapp.presentation.WeatherMainActivity
import com.example.myweatherapp.presentation.viewmodel.LocationViewModel
import com.example.myweatherapp.presentation.viewmodel.WeatherViewModel

class LocationFragment : Fragment(R.layout.fragment_locations) {

    private lateinit var viewModel: LocationViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as WeatherMainActivity).locationViewModel
    }
}