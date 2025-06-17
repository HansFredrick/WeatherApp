package com.example.myweatherapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myweatherapp.R
import com.example.myweatherapp.domain.models.forecast.wrapper.ForecastWithDayAndHours

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.ContentViewHolder>() {

    inner class ContentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    private val differCallback = object : DiffUtil.ItemCallback<ForecastWithDayAndHours>() {
        override fun areItemsTheSame(oldItem: ForecastWithDayAndHours, newItem: ForecastWithDayAndHours): Boolean {
            return oldItem.forecastday.date == newItem.forecastday.date // date identifies a unique forecast day
        }

        override fun areContentsTheSame(oldItem: ForecastWithDayAndHours, newItem: ForecastWithDayAndHours): Boolean {
            return oldItem == newItem  // check full content equality
        }
    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.day_item_forecast, parent, false)
        return ContentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val forecast = differ.currentList[position]

        val textViewDate = holder.itemView.findViewById<TextView>(R.id.textViewDate)
        val textViewCondition = holder.itemView.findViewById<TextView>(R.id.textViewCondition)
        val imageViewIcon = holder.itemView.findViewById<ImageView>(R.id.imageViewIcon)
        val textViewTempUp = holder.itemView.findViewById<TextView>(R.id.textViewTempUp)
        val textViewTempDown = holder.itemView.findViewById<TextView>(R.id.textViewTempDown)

        // Set values
        textViewDate.text = forecast.forecastday.date
        textViewCondition.text = forecast.day?.condition?.text ?: "N/A"
        textViewTempUp.text = "${forecast.day?.maxtemp_c?.toInt() ?: 0}°"
        textViewTempDown.text = "${forecast.day?.mintemp_c?.toInt() ?: 0}°"

        // Load weather icon
        val iconUrl = "https:${forecast.day?.condition?.icon ?: 0}"
        Glide.with(holder.itemView.context)
            .load(iconUrl)
            .into(imageViewIcon)
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}