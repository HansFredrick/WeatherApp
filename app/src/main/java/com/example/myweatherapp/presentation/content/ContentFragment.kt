package com.example.myweatherapp.presentation.content

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.myweatherapp.R
import com.example.myweatherapp.presentation.WeatherMainActivity
import com.example.myweatherapp.presentation.viewmodelold.WeatherViewModel

class ContentFragment : Fragment(R.layout.fragment_content) {
//    private lateinit var locationViewModel: LocationViewModel
    private lateinit var weatherViewModel: WeatherViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        locationViewModel = (activity as WeatherMainActivity).locationViewModel
        weatherViewModel = (activity as WeatherMainActivity).weatherViewModel
    }
}