package com.example.waterman_son

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.stream.IntStream.range

class WaterManSonViewModel: ViewModel() {
    private val testCaseCup = WaterCupSon("Date", 0.0, "Time")

    private val _waterInfo = MutableLiveData(mutableListOf(testCaseCup))
    val waterInfo: LiveData<MutableList<WaterCupSon>>
        get() = _waterInfo
    private val _waterTotal = MutableLiveData(0.0)
    val waterTotal: LiveData<Double>
        get() = _waterTotal

    fun setWaterTotalSon() {
        for (x in _waterInfo.value ?: listOf(testCaseCup)){
            _waterTotal.value = _waterTotal.value?.plus(x.waterAmount)
        }
    }

    fun addItemSon(newWaterCupSon: WaterCupSon) {
        _waterInfo.value?.add(newWaterCupSon)
        _waterTotal.value = 0.0
    }

    fun reset() {
        _waterInfo.value = mutableListOf(testCaseCup)
        _waterTotal.value = 0.0
    }

    fun setWaterInfoWithSignin(userEmailInfo: String) {

    }
}