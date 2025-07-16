package com.example.myweatherapp.presentation.location

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myweatherapp.databinding.ListItemLocationBinding
import com.example.myweatherapp.domain.models.currentweather.WeatherLocation


class LocationAdapter :
        ListAdapter<WeatherLocation, LocationAdapter.ContentViewHolder>(differCallback) {

    inner class ContentViewHolder(val binder: ListItemLocationBinding) :
        RecyclerView.ViewHolder(binder.root)

    companion object{
        private val differCallback = object : DiffUtil.ItemCallback<WeatherLocation>() {
            override fun areItemsTheSame(
                oldItem: WeatherLocation,
                newItem: WeatherLocation
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: WeatherLocation,
                newItem: WeatherLocation
            ): Boolean {
                return oldItem == newItem  // check full content equality
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val binder = ListItemLocationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false)
        return ContentViewHolder(binder)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val location = getItem(position)

        holder.binder.textViewLocationName.text = location.name ?: "Unknown Location"
        holder.binder.textViewLocationTime.text = location.localTime ?: "Unknown Local Time"
        holder.binder.textViewCountry.text = location.country ?: "Unknown Country"

        setOnItemClickListener {
            onItemClickListener?.let{it(location)}
        }
    }

    private var onItemClickListener :((WeatherLocation) -> Unit)? = null

    fun setOnItemClickListener (listener: (WeatherLocation) -> Unit){
        onItemClickListener = listener
    }
}