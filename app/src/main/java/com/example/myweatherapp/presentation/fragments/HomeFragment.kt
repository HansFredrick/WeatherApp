package com.example.myweatherapp.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myweatherapp.R
import com.example.myweatherapp.presentation.adapters.WeatherAdapter

class HomeFragment : Fragment(R.layout.fragment_home) {

//    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var weatherAdapter: WeatherAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize your adapter
        weatherAdapter = WeatherAdapter()

        // Setup RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewForecast)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = weatherAdapter

//        // Observe forecast list from ViewModel
//        viewModel.forecastList.observe(viewLifecycleOwner) { forecastList ->
//            weatherAdapter.differ.submitList(forecastList)
//        }
    }
}