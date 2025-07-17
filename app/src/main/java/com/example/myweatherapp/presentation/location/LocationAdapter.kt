package com.example.myweatherapp.presentation.location

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myweatherapp.databinding.ListItemLocationBinding
import com.example.myweatherapp.domain.models.location.Location


class LocationAdapter : ListAdapter<Location, LocationAdapter.ContentViewHolder>(differCallback) {

    private var onItemClickListener :((Location) -> Unit)? = null

    fun setOnItemClickListener(listener: (Location) -> Unit) {
        onItemClickListener = listener
    }

    inner class ContentViewHolder(val binder: ListItemLocationBinding) :  RecyclerView.ViewHolder(binder.root){
        private var currentLocation: Location? = null
        init {
            binder.root.setOnClickListener {
                currentLocation?.let { location ->
                    onItemClickListener?.invoke(location)
                }
            }
        }
    }

    companion object{ private val differCallback = object : DiffUtil.ItemCallback<Location>() {

            override fun areItemsTheSame(
                oldItem: Location,
                newItem: Location
            ): Boolean {
                return oldItem.placeId == newItem.placeId
            }

            override fun areContentsTheSame(
                oldItem: Location,
                newItem: Location
            ): Boolean {
                return oldItem == newItem
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

        holder.binder.tvLocationDisplayName.text = location.displayName ?: "Unknown Location"
        holder.binder.tvLocationLongitude.text = location.longitude ?: "Unknown Latitude"
        holder.binder.tvLocationLatitude.text = location.latitude ?: "Unknown Longitude"

    }
}