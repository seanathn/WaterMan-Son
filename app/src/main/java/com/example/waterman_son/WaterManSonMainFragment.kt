package com.example.waterman_son

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import com.example.waterman_son.databinding.FragmentWatermanSonBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


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

        b.clearSon.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext()).setTitle("Are you sure you want to reset?")
                .setMessage("The information will be lost").setPositiveButton("Yes"){ dialog, which ->
                    viewModel.reset()
                }.setNegativeButton("No"){dialog, which ->
                }.show()
        }

        viewModel.waterInfo.observe(viewLifecycleOwner) { waterList ->
            val adapter = WaterCupSonAdapter(waterList)
            b.son.adapter = adapter
            viewModel.setWaterTotalSon()
        }

        viewModel.waterTotal.observe(viewLifecycleOwner) {
            b.totalAmountText.text = viewModel.waterTotal.value?.toString() ?: "0.0"
        }
        setHasOptionsMenu(true)

        return b.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.
        onNavDestinationSelected(item, requireView().findNavController()) || super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}