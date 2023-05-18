package com.example.waterman_son

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.waterman_son.databinding.FragmentChangeValueBinding


class ChangeValueFragment : Fragment() {
    private var _binding: FragmentChangeValueBinding? = null
    private val b get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChangeValueBinding.inflate(inflater, container, false)

        return b.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}