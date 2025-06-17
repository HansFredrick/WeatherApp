package com.example.myweatherapp.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myweatherapp.R
import com.example.myweatherapp.presentation.WeatherMainActivity
import com.example.myweatherapp.presentation.adapters.WeatherAdapter
import com.example.myweatherapp.presentation.viewmodel.WeatherViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var viewModel: WeatherViewModel
    private lateinit var weatherAdapter: WeatherAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as WeatherMainActivity).weatherViewModel

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