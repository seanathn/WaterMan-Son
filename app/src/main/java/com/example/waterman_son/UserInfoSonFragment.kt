package com.example.waterman_son

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.waterman_son.databinding.FragmentUserInfoSonBinding

class UserInfoSonFragment : Fragment() {
    private var _binding: FragmentUserInfoSonBinding? = null
    private val b get() = _binding!!

    private val viewModel: WaterManSonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserInfoSonBinding.inflate(inflater, container, false)



        return b.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}