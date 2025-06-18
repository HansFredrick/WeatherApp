package com.example.myweatherapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myweatherapp.databinding.DayItemForecastBinding
import com.example.myweatherapp.domain.models.forecast.wrapper.ForecastWithDayAndHours

class WeatherAdapter :
    ListAdapter<ForecastWithDayAndHours, WeatherAdapter.ContentViewHolder>(differCallback) {

    inner class ContentViewHolder(val binder: DayItemForecastBinding) :
        RecyclerView.ViewHolder(binder.root)

    companion object {
        private val differCallback = object : DiffUtil.ItemCallback<ForecastWithDayAndHours>() {
            override fun areItemsTheSame(
                oldItem: ForecastWithDayAndHours,
                newItem: ForecastWithDayAndHours
            ): Boolean {
                return oldItem.forecastday.date == newItem.forecastday.date // date identifies a unique forecast day
            }

            override fun areContentsTheSame(
                oldItem: ForecastWithDayAndHours,
                newItem: ForecastWithDayAndHours
            ): Boolean {
                return oldItem == newItem  // check full content equality
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val binder = DayItemForecastBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ContentViewHolder(binder)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val forecast = getItem(position)

        // Set values
        holder.binder.textViewDate.text = forecast.forecastday.date
        holder.binder.textViewCondition.text = forecast.day?.condition?.text ?: "N/A"
        holder.binder.textViewTempUp.text = "${forecast.day?.maxtemp_c?.toInt() ?: 0}°"
        holder.binder.textViewTempDown.text = "${forecast.day?.mintemp_c?.toInt() ?: 0}°"

        // Load weather icon
        val iconUrl = "https:${forecast.day?.condition?.icon ?: 0}"
        Glide.with(holder.itemView.context)
            .load(iconUrl)
            .into(holder.binder.imageViewIcon)
    }

}