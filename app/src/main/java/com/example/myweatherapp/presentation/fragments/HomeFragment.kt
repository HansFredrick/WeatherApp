package com.example.myweatherapp.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myweatherapp.R
import com.example.myweatherapp.domain.models.forecast.wrapper.ForecastWithDayAndHours
import com.example.myweatherapp.presentation.WeatherMainActivity
import com.example.myweatherapp.presentation.adapters.WeatherAdapter
import com.example.myweatherapp.presentation.viewmodel.WeatherViewModel
import com.example.myweatherapp.util.Resource

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var viewModel: WeatherViewModel
    private lateinit var weatherAdapter: WeatherAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as WeatherMainActivity).weatherViewModel
        setUpAdapter(view)

//        viewModel.weatherForecast.observe(viewLifecycleOwner, Observer{ response ->
//            when(response){
//                is Resource.Success -> {
//                    response.data?.let { forecast ->
//                        val list = forecast.forecast.forecastday.map { forecastDay ->
//                            ForecastWithDayAndHours(
//                                forecastday = forecastDay,
//                                day = forecastDay.day,
//                                hours = forecastDay.hour
//                            )
//                        }
//                        weatherAdapter.submitList(list)
//                    }
//                    }
//                }
//
//                else -> {}
//            }
//        })
    }
    private fun setUpAdapter(view:View){
        weatherAdapter = WeatherAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewForecast)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = weatherAdapter

    }
}