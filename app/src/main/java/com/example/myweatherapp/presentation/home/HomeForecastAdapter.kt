package com.example.myweatherapp.presentation.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myweatherapp.databinding.DayItemForecastBinding
import com.example.myweatherapp.domain.models.forecast.ForecastDay

class HomeForecastAdapter :
    ListAdapter<ForecastDay, HomeForecastAdapter.ContentViewHolder>(differCallback) {

    inner class ContentViewHolder(val binder: DayItemForecastBinding) :
        RecyclerView.ViewHolder(binder.root)

    companion object {
        private val differCallback = object : DiffUtil.ItemCallback<ForecastDay>() {
            override fun areItemsTheSame(
                oldItem: ForecastDay,
                newItem: ForecastDay
            ): Boolean {
                return oldItem.date== newItem.date // date identifies a unique forecast day
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: ForecastDay,
                newItem: ForecastDay
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
        holder.binder.tvDayItemDate.text = forecast.date
        holder.binder.tvDayItemCondition.text = forecast.day?.dayCondition?.text ?: "N/A"
        holder.binder.tvDayItemMaxTemp.text = "${forecast.day?.maximumTemperatureCelsus.toString()?: 0}°"
        holder.binder.tvDayItemAveTemp.text = "${forecast.day?.averageTemperatureCelsus.toString()?: 0}°"

        // Load weather icon
        val iconUrl = "https:${forecast.day?.dayCondition?.icon ?: 0}"
        Glide.with(holder.itemView.context)
            .load(iconUrl)
            .into(holder.binder.ivDayItemIcon)
    }

}