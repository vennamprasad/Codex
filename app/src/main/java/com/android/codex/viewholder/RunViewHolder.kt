package com.android.codex.viewholder

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.android.codex.R
import com.android.codex.data.Run
import com.android.codex.others.TrackingUtility
import com.bumptech.glide.Glide
import smartadapter.viewholder.SmartViewHolder
import java.text.SimpleDateFormat
import java.util.*

class RunViewHolder(parentView: ViewGroup) :
    SmartViewHolder<Run>(parentView, R.layout.item_run) {
    private val ivRunImage: ImageView = itemView.findViewById(R.id.ivRunImage)
    private val tvDate: TextView = itemView.findViewById(R.id.tvDate)
    private val tvTime: TextView = itemView.findViewById(R.id.tvTime)
    private val tvDistance: TextView = itemView.findViewById(R.id.tvDistance)
    private val tvAvgSpeed: TextView = itemView.findViewById(R.id.tvAvgSpeed)
    private val tvCalories: TextView = itemView.findViewById(R.id.tvCalories)
    @SuppressLint("SetTextI18n")
    override fun bind(item: Run) {
        Glide.with(ivRunImage)
            .load(item.img)
            .into(ivRunImage)
        val calendar = Calendar.getInstance().apply {
            timeInMillis = item.timestamp
        }
        val dateFormat = SimpleDateFormat("dd MM yyyy", Locale.getDefault())
        tvDate.text = dateFormat.format(calendar.time)

        tvDistance.text=(item.distanceInMeters/1000f).toString()+"km"
        tvAvgSpeed.text=(item.avgSpeedInKMH).toString()+"km/h"
        tvCalories.text=item.caloriesBurned.toString()+"kcal"
        tvTime.text=TrackingUtility.getFormattedStopWatchTime(item.timeInMillis)

    }

    override fun unbind() {
        Glide.with(ivRunImage).clear(ivRunImage)
        tvDate.text=""
        tvTime.text=""
        tvDistance.text=""
        tvAvgSpeed.text=""
        tvCalories.text=""
    }
}