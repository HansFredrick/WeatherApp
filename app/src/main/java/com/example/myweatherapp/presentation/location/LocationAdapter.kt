package com.example.myweatherapp.presentation.location

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myweatherapp.databinding.ListItemLocationBinding
import com.example.myweatherapp.domain.models.current.roomentities.Location


class LocationAdapter :
        ListAdapter<Location, LocationAdapter.ContentViewHolder>(differCallback) {

    inner class ContentViewHolder(val binder: ListItemLocationBinding) :
        RecyclerView.ViewHolder(binder.root)

    companion object{
        private val differCallback = object : DiffUtil.ItemCallback<Location>() {
            override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
                return oldItem.country == newItem.country// date identifies a unique forecast day
            }

            override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
                return oldItem == newItem  // check full content equality
            }
        }
    }


    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val binder = ListItemLocationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false)
        return ContentViewHolder(binder)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val location = getItem(position)

//        val nameTextView = holder.itemView.findViewById<TextView>(R.id.textViewLocationName)
//        val countryTextView = holder.itemView.findViewById<TextView>(R.id.textViewCountry)
//        val timeTextView = holder.itemView.findViewById<TextView>(R.id.textViewLocationTime)

        // Safe setting of name
        holder.binder.textViewLocationName.text = location.name ?: "Unknown Location"

        // Safe setting of time
        holder.binder.textViewLocationTime.text = location.localtime ?: "Unknown Local Time"

        // Safe setting of  country
        holder.binder.textViewCountry.text = location.country ?: "Unknown Country"

        setOnItemClickListener {
            onItemClickListener?.let{it(location)}
        }
    }

    private var onItemClickListener :((Location) -> Unit)? = null

    fun setOnItemClickListener (listener: (Location) -> Unit){
        onItemClickListener = listener
    }
}