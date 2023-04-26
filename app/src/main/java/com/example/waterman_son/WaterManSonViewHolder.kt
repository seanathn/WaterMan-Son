package com.example.waterman_son

import androidx.recyclerview.widget.RecyclerView
import com.example.waterman_son.databinding.ListItemLayoutBinding

class WaterManSonViewHolder(val b: ListItemLayoutBinding) : RecyclerView.ViewHolder(b.root) {

    private lateinit var currentInfo: WaterCupSon

    fun bindWaterInfo(waterInfo: WaterCupSon) {
        currentInfo = waterInfo
        b.dateRecycler.text = currentInfo.date
        b.amountRecycler.text = currentInfo.waterAmount.toString()
        b.timeRecyler.text = currentInfo.time
    }
}