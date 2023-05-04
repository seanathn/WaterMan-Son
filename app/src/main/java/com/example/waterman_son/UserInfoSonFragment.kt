package com.example.waterman_son

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.waterman_son.databinding.FragmentUserInfoSonBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UserInfoSonFragment : Fragment() {
    private var _binding: FragmentUserInfoSonBinding? = null
    private val b get() = _binding!!
    lateinit var dbRef : DatabaseReference

    private val viewModel: WaterManSonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserInfoSonBinding.inflate(inflater, container, false)

        dbRef = Firebase.database.reference

        b.moveOnSon.setOnClickListener {
            val userInfo = WaterCupSon(b.userData.text.toString(), b.userNumber.text.toString().toDouble(), b.userTime.text.toString())
            viewModel.addItemSon(userInfo)
            dbRef.child("waterList").push().setValue(viewModel.waterInfo)
            b.root.findNavController().navigateUp()
        }

        return b.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}