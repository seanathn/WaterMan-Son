package com.example.waterman_son

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.waterman_son.databinding.ListItemLayoutBinding

class WaterCupSonAdapter(val waterCupList: MutableList<WaterCupSon>) : RecyclerView.Adapter<WaterManSonViewHolder>() {
    override fun onBindViewHolder(holder: WaterManSonViewHolder, position: Int) {
        val currentWaterInfo = waterCupList[position]
        if (waterCupList.contains(WaterCupSon("Date", 0.0, "Time"))) {
            waterCupList.remove(WaterCupSon("Date", 0.0, "Time"))
        }
        holder.bindWaterInfo(currentWaterInfo)
    }

    override fun getItemCount(): Int {
        return waterCupList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaterManSonViewHolder {
        val b = ListItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WaterManSonViewHolder(b)
    }
}