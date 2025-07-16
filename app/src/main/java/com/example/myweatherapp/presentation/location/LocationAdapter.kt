package com.example.myweatherapp.presentation.location

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myweatherapp.databinding.ListItemLocationBinding
import com.example.myweatherapp.domain.models.location.Location


class LocationAdapter :
        ListAdapter<Location, LocationAdapter.ContentViewHolder>(differCallback) {

    inner class ContentViewHolder(val binder: ListItemLocationBinding) :
        RecyclerView.ViewHolder(binder.root)

    companion object{
        private val differCallback = object : DiffUtil.ItemCallback<Location>() {
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

        holder.binder.textViewLocationName.text = location.displayName ?: "Unknown Location"
        holder.binder.textViewLocationTime.text = location.longitude ?: "Unknown Local Time"
        holder.binder.textViewCountry.text = location.latitude ?: "Unknown Country"

        setOnItemClickListener {
            onItemClickListener?.let{it(location)}
        }
    }

    private var onItemClickListener :((Location) -> Unit)? = null

    fun setOnItemClickListener (listener: (Location) -> Unit){
        onItemClickListener = listener
    }
}