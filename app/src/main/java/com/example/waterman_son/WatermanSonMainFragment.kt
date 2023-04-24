package com.example.waterman_son

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.waterman_son.databinding.FragmentWatermanSonBinding


class WatermanSonMainFragment : Fragment() {
    private var _binding : FragmentWatermanSonBinding? = null
    private val b get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWatermanSonBinding.inflate(inflater, container, false)

        b.newWaterInfoSon.setOnClickListener {
            val action = WatermanSonMainFragmentDirections.actionWatermanSonMainFragmentToUserInfoSonFragment()
            b.root.findNavController().navigate(action)
        }

        return b.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}