package com.example.myweatherapp.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myweatherapp.R
import com.example.myweatherapp.databinding.FragmentHomeBinding
import com.example.myweatherapp.presentation.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var homeForecastAdapter: HomeForecastAdapter
    private lateinit var binder: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = FragmentHomeBinding.inflate(inflater, container, false)
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binder.refreshLayout.setOnRefreshListener {
            viewModel.getHomeData()
            binder.refreshLayout.isRefreshing = false
        }


        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel._uiState.collect { state ->

                    binder.pbHome.isVisible = state.isLoading

                    binder.tvLocationName.text = state.weather?.weatherLocation?.name
                    binder.tvLocationCountry.text = state.weather?.weatherLocation?.country
                    binder.tvLocationTimeZone.text = state.weather?.weatherLocation?.timeZone
                    binder.tvLocationTime.text = state.weather?.weatherLocation?.localTime

                    binder.tvCurrentTemperature.text =
                        state.weather?.current?.temperatureCelsius.toString()

                    binder.tvCurrentWeatherCondition.text =
                        state.weather?.current?.currentWeatherCondition?.text

                    val imageUrl = state.weather?.current?.currentWeatherCondition?.icon
                    //https://cdn.weatherapi.com/weather/64x64/day/353.png
                    Glide.with(requireContext()).load("https:$imageUrl")
                        .into(binder.ivCurrentConditionIcon)

                    binder.tvCurrentAirQuality.text =
                        state.weather?.current?.airQuality?.usEPAAirQualityIndex.toString()
                    binder.tvCurrentWindSpeedKilometers.text =
                        state.weather?.current?.windSpeedKilometers.toString()
                    binder.tvCurrentWindSpeedMiles.text =
                        state.weather?.current?.windSpeedMiles.toString()
                    binder.tvCurrentPrecipitationMm.text =
                        state.weather?.current?.precipitationMilimeter.toString()
                    binder.tvCurrentPrecipitationInch.text =
                        state.weather?.current?.precipitationInch.toString()
                    binder.tvCurrentUVIndex.text = state.weather?.current?.uvIndex.toString()

                    homeForecastAdapter = HomeForecastAdapter().also { adapter ->
                        binder.recyclerViewForecast.adapter = adapter
                        binder.recyclerViewForecast.layoutManager =
                            LinearLayoutManager(requireContext())
                    }


                    state.forecastDays?.let { days ->
                        Log.d("HomeFragment", "Submitting ${days.size} days to adapter")
                        homeForecastAdapter.submitList(days)
                    }

                }
            }

        }
//        binder.btnLogin.setonClickListener{
//            viewModel.setIntent(
//                intent = HomeIntent.OnLoginClicked
//            )
//        }


    }
}