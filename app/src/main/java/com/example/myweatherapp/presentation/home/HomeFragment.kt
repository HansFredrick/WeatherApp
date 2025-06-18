package com.example.myweatherapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myweatherapp.R
import com.example.myweatherapp.databinding.FragmentHomeBinding
import com.example.myweatherapp.presentation.adapters.WeatherAdapter
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var weatherAdapter: WeatherAdapter
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

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    binder.tvLocationName.text = state.weather?.location?.name
                    binder.tvLocationCountry.text = state.weather?.location?.country
                    binder.tvLocationTimeZone.text = state.weather?.location?.timeZone
                    binder.tvLocationTime.text = state.weather?.location?.localTime

                    binder.tvCurrentTemperature.text =
                        state.weather?.current?.temperatureCelsius.toString()
                    binder.tvCurrentWeatherCondition.text =
                        state.weather?.current?.currentWeatherCondition?.text
                    val imageUrl = state.weather?.current?.currentWeatherCondition?.icon
                    //https://cdn.weatherapi.com/weather/64x64/day/353.png
                    Glide.with(requireContext()).load("https:$imageUrl").into(binder.ivCurrentConditionIcon)


                }
            }

        }
    }
}