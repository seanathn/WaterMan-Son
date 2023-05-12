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
import java.util.stream.IntStream.range

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
        for (x in _waterInfo.value ?: listOf(testCaseCup)){
            _waterTotal.value = _waterTotal.value?.plus(x.waterAmount)
        }
    }

    fun addItemSon(newWaterCupSon: WaterCupSon) {
        _waterInfo.value?.add(newWaterCupSon)
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
                val userWaterInfo = dataSnapshot.child("users").child(FirebaseAuth.getInstance().uid.toString()).child("waterList").child("value").children

                for (fireBaseInfo in userWaterInfo) {
                    val time = fireBaseInfo.child("time").getValue().toString()
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
}