package com.example.waterman_son

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.waterman_son.databinding.FragmentWatermanSonBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth


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
                .setMessage("The information will be lost").setPositiveButton("Yes"){ _, _ ->
                    viewModel.reset()
                }.setNegativeButton("No"){ _, _ ->
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
        if (item.title.toString() == "About"){
            return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) || super.onOptionsItemSelected(item)
        }
        else if (item.title.toString() == "Sign Out"){
            FirebaseAuth.getInstance().signOut()
            b.root.findNavController().navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}