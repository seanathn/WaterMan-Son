package com.example.waterman_son

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.waterman_son.databinding.FragmentWatermanSonBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class WaterManSonMainFragment : Fragment() {
    private var _binding : FragmentWatermanSonBinding? = null
    private val b get() = _binding!!
    private val viewModel: WaterManSonViewModel by activityViewModels()
    lateinit var dbRef : DatabaseReference
    lateinit var auth : FirebaseAuth

    override fun onStart() {
        super.onStart()
        // Check if user is signed in
        val currentUser = auth.currentUser
        if (currentUser != null) {
            var userListOfWater = dbRef.child("users").child(currentUser.uid).get().result.value
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWatermanSonBinding.inflate(inflater, container, false)

        dbRef = Firebase.database.reference
        auth = FirebaseAuth.getInstance()

        b.newWaterInfoSon.setOnClickListener {
            val action = WaterManSonMainFragmentDirections.actionWatermanSonMainFragmentToUserInfoSonFragment()
            b.root.findNavController().navigate(action)
        }

        b.clearSon.setOnClickListener {
            viewModel.reset()
        }

        viewModel.waterInfo.observe(viewLifecycleOwner) { waterList ->
            val adapter = WaterCupSonAdapter(waterList)
            b.son.adapter = adapter
            viewModel.setWaterTotalSon()
        }

        viewModel.waterTotal.observe(viewLifecycleOwner) {
            b.totalAmountText.text = viewModel.waterTotal.value?.toString() ?: "0.0"
        }

        return b.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}