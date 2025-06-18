package com.example.myweatherapp.presentation.location

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.myweatherapp.R

class LocationFragment : Fragment(R.layout.fragment_locations) {

//    private lateinit var viewModel: LocationViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel = (activity as WeatherMainActivity).locationViewModel
    }
}