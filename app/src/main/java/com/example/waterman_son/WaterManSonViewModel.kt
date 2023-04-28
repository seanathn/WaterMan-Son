package com.example.waterman_son

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.stream.IntStream.range

class WaterManSonViewModel: ViewModel() {
    private val testCaseCup = WaterCupSon("test", 0.0, "test")

    private val _waterInfo = MutableLiveData(mutableListOf(testCaseCup))
    val waterInfo: LiveData<MutableList<WaterCupSon>>
        get() = _waterInfo
    private val _waterTotal = MutableLiveData(0.0)
    val waterTotal: LiveData<Double>
        get() = _waterTotal

    fun setWaterTotalSon() {
        for (x in _waterInfo.value ?: listOf(testCaseCup)){
            _waterTotal.value = x.waterAmount
        }
    }

    fun addItemSon(newWaterCupSon: WaterCupSon) {
        _waterInfo.value?.add(newWaterCupSon)
    }

    fun reset() {
        _waterInfo.value = mutableListOf(testCaseCup)
        _waterTotal.value = 0.0
    }
}