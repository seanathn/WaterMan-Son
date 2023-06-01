package com.example.waterman_son

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.math.RoundingMode
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class WaterManSonViewModel: ViewModel() {
    private val testCaseCup = WaterCupSon("Date", 0.0, "Time")

    private val _waterInfo = MutableLiveData(mutableListOf(testCaseCup))
    val waterInfo: LiveData<MutableList<WaterCupSon>>
        get() = _waterInfo
    private val _waterTotal = MutableLiveData(0.0)
    val waterTotal: LiveData<Double>
        get() = _waterTotal

    private var tempList = mutableListOf<WaterCupSon>()

    fun setWaterTotalSon() {
        var currentWaterAmount = 0.0
        for (x in _waterInfo.value ?: listOf(testCaseCup)){
            currentWaterAmount = currentWaterAmount.plus(x.waterAmount)
        }
        val amount = currentWaterAmount.toBigDecimal()
        val decimal = amount.setScale(2, RoundingMode.HALF_EVEN)
        _waterTotal.value = decimal.toDouble()
    }

    fun addItemSon(newWaterCupSon: WaterCupSon) {
        _waterInfo.value?.add(newWaterCupSon)
        _waterTotal.value = 0.0
        orderList()
    }

    fun reset() {
        _waterInfo.value = mutableListOf(testCaseCup)
        _waterTotal.value = 0.0
        Firebase.database.reference.child("users").child(FirebaseAuth.getInstance().uid.toString())
            .child("waterList").setValue(_waterInfo)
    }

    fun setWaterInfoWithSignIn() {
        Firebase.database.reference.addValueEventListener(object: ValueEventListener  {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val userWaterInfo = dataSnapshot.child("users")
                    .child(FirebaseAuth.getInstance().uid.toString())
                    .child("waterList").child("value").children

                for (fireBaseInfo in userWaterInfo) {
                    val time = fireBaseInfo.child("time").value.toString()
                    val date = fireBaseInfo.child("date").getValue().toString()
                    val amountString = fireBaseInfo.child("waterAmount").getValue().toString()
                    val amount = amountString.toDouble()
                    val currentWaterUserInfo = WaterCupSon(date, amount, time)
                    tempList.add(currentWaterUserInfo)
                }
                _waterInfo.value = tempList
                tempList = mutableListOf<WaterCupSon>()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("WaterCupSon", "Failed to read value.", error.toException())
            }
        })
    }

    fun removeItem(item: WaterCupSon) {
        _waterInfo.value?.removeAt(_waterInfo.value?.indexOf(item)!!)
    }

    fun replaceItem(itemIndex: Int, item: WaterCupSon) {
        _waterInfo.value?.set(itemIndex,item)
    }

    fun orderList() {
        var yearList = mutableListOf<String>()
        for (x in _waterInfo.value!!) {
            yearList.add(x.date)
        }


    }

    fun sort(array: MutableList<String>) {
//        for (i in 1 until array.size) {
//            val x = array[i]
//
//            // Find location to insert
//            // using binary search
//            val j: Int = Math.abs(
//                Arrays.binarySearch(
//                    array, 0,
//                    i, x
//                ) + 1
//            )
//
//            // Shifting array to one
//            // location right
//            System.arraycopy(
//                array, j,
//                array, j + 1, i - j
//            )
//
//            // Placing element at its
//            // correct location
//            array[j] = x
//        }
    }
}