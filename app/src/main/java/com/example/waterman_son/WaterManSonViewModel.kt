package com.example.waterman_son

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WaterManSonViewModel: ViewModel() {
    private val _waterInfo = MutableLiveData(listOf(WaterCupSon("", 0.0, "")))
    val waterInfo: LiveData<List<WaterCupSon>>
        get() = _waterInfo
    private val _waterTotal = MutableLiveData(0.0)
    val waterTotal: LiveData<Double>
        get() = _waterTotal
    private var _currentIndex = 0
    val currentIndex: Int
        get() = _currentIndex

    fun setWaterTotalSon() {
        _waterTotal.value = _waterInfo.value?.get(_currentIndex)?.waterAmount
    }

    fun addItem() {

    }
}