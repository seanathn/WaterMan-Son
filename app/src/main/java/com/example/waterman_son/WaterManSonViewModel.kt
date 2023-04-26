package com.example.waterman_son

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.stream.IntStream.range

class WaterManSonViewModel: ViewModel() {
    private val _waterInfo = MutableLiveData(mutableListOf(WaterCupSon("", 0.0, "")))
    val waterInfo: LiveData<MutableList<WaterCupSon>>
        get() = _waterInfo
    private val _waterTotal = MutableLiveData(0.0)
    val waterTotal: LiveData<Double>
        get() = _waterTotal
    private var _currentIndex = 0
    val currentIndex: Int
        get() = _currentIndex
    private var _takenInUser = false
    val takenInUser: Boolean
        get() = _takenInUser

    fun setWaterTotalSon() {
        for (x in _waterInfo.value ?: listOf(WaterCupSon("", 0.0, ""))){
            _waterTotal.value = x.waterAmount
        }
    }

    fun addItemSon(newWaterCupSon: WaterCupSon) {
        _waterInfo.value?.add(newWaterCupSon)
        _currentIndex++
        _takenInUser = true
    }

    fun reset() {
        _waterInfo.value?.clear()
        _currentIndex = 0
    }
}