package com.example.myweatherapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myweatherapp.R
import com.example.myweatherapp.domain.model.current.Location

class LocationAdapter : RecyclerView.Adapter<LocationAdapter.ContentViewHolder>() {

    inner class ContentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    private val differCallback = object : DiffUtil.ItemCallback<Location>() {
        override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem.country == newItem.country// date identifies a unique forecast day
        }

        override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem == newItem  // check full content equality
        }
    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_location, parent, false)
        return ContentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val location = differ.currentList[position]

        val nameTextView = holder.itemView.findViewById<TextView>(R.id.textViewLocationName)
        val countryTextView = holder.itemView.findViewById<TextView>(R.id.textViewCountry)
        val timeTextView = holder.itemView.findViewById<TextView>(R.id.textViewLocationTime)

        // Safe setting of name
        nameTextView.text = location.name ?: "Unknown Location"

        // Safe setting of time
        timeTextView.text = location.localtime ?: "Unknown Local Time"

        // Safe setting of  country
        countryTextView.text = location.country ?: "Unknown Country"

        setOnItemClickListener {
            onItemClickListener?.let{it(location)}
        }
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener :((Location) -> Unit)? = null

    fun setOnItemClickListener (listener: (Location) -> Unit){
        onItemClickListener = listener
    }
}