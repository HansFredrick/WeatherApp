package com.example.myweatherapp.presentation.location

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myweatherapp.R
import com.example.myweatherapp.databinding.FragmentHomeBinding
import com.example.myweatherapp.databinding.FragmentLocationsBinding
import com.example.myweatherapp.presentation.home.HomeForecastAdapter
import com.example.myweatherapp.presentation.viewmodel.LocationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocationFragment : Fragment(R.layout.fragment_locations) {

    private val viewModel: LocationViewModel by viewModels()
    private lateinit var locationAdapter: LocationAdapter
    private lateinit var binder: FragmentLocationsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = FragmentLocationsBinding.inflate(inflater, container, false)
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binder.refreshLayout.setOnRefreshListener {
            viewModel.getData()
            binder.refreshLayout.isRefreshing = false
        }
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel._uiState.collect { state ->
                    locationAdapter = LocationAdapter().also { adapter ->
                        binder.rvLocations.adapter = adapter
                        binder.rvLocations.layoutManager =
                            LinearLayoutManager(requireContext())
                    }


                    state.visitedLocation?.let { locations ->
                        Log.d("HomeFragment", "Submitting ${locations.size} locations to adapter")
                        locationAdapter.submitList(locations)
                    }


                }


            }
        }


    }
}