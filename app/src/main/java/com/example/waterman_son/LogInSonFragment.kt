package com.example.waterman_son

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.waterman_son.databinding.FragmentLogInSonBinding

class LogInSonFragment : Fragment() {
    private var _binding : FragmentLogInSonBinding? = null
    private val b get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLogInSonBinding.inflate(inflater, container,false)



        return b.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}