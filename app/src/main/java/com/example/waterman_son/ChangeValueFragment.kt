package com.example.waterman_son

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.waterman_son.databinding.FragmentChangeValueBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class ChangeValueFragment : Fragment() {
    private var _binding: FragmentChangeValueBinding? = null
    private val b get() = _binding!!
    private val viewModel: WaterManSonViewModel by activityViewModels()
    private val dbRef = Firebase.database.reference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChangeValueBinding.inflate(inflater, container, false)

        val args = ChangeValueFragmentArgs.fromBundle(requireArguments())
        val item = WaterCupSon(args.date, args.amount.toDouble(), args.toString())

        b.removeItem.setOnClickListener {
            viewModel.removeItem(item)
            dbRef.child("users").child(FirebaseAuth.getInstance().uid.toString()).child("waterList").setValue(viewModel.waterInfo)
            b.root.findNavController().navigateUp()
        }

        b.moveOnSon.setOnClickListener {

        }

        return b.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}