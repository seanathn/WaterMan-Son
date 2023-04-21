package com.example.waterman_son

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.waterman_son.databinding.FragmentLogInSonBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LogInSonFragment : Fragment() {
    private var _binding : FragmentLogInSonBinding? = null
    private val b get() = _binding!!
    lateinit var dbRef : DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLogInSonBinding.inflate(inflater, container,false)

        dbRef = Firebase.database.reference

        return b.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}