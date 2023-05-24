package com.example.waterman_son

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.waterman_son.databinding.ListItemLayoutBinding

class WaterManSonViewHolder(val b: ListItemLayoutBinding) : RecyclerView.ViewHolder(b.root) {

    init{
        b.root.setOnClickListener{
            val action = WaterManSonMainFragmentDirections.actionWaterManSonMainFragmentToChangeValueFragment(currentInfo.date, currentInfo.time, currentInfo.waterAmount.toFloat())
            b.root.findNavController().navigate(action)}
    }

    private lateinit var currentInfo: WaterCupSon

    fun bindWaterInfo(waterInfo: WaterCupSon) {
        currentInfo = waterInfo
        val userNum = "%.2f".format(currentInfo.waterAmount)
        b.dateRecycler.text = currentInfo.date
        b.amountRecycler.text = userNum
        b.timeRecyler.text = currentInfo.time
    }
}