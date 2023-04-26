package com.example.waterman_son

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.waterman_son.databinding.FragmentWatermanSonBinding


class WaterManSonMainFragment : Fragment() {
    private var _binding : FragmentWatermanSonBinding? = null
    private val b get() = _binding!!
    private val viewModel: WaterManSonViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWatermanSonBinding.inflate(inflater, container, false)

        b.newWaterInfoSon.setOnClickListener {
            val action = WaterManSonMainFragmentDirections.actionWatermanSonMainFragmentToUserInfoSonFragment()
            b.root.findNavController().navigate(action)
        }
        if (viewModel.takenInUser){
            val adapter = WaterCupSonAdapter(viewModel.waterInfo.value!!)
            b.son.adapter = adapter
        }

        return b.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}