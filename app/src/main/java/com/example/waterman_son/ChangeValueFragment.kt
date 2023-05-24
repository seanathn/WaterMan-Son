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
import java.math.RoundingMode


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
        val amount = args.amount.toDouble().toBigDecimal()
        val decimal = amount.setScale(2, RoundingMode.HALF_EVEN)
        val item = WaterCupSon(args.date, decimal.toDouble(), args.time)
        val index = viewModel.waterInfo.value?.indexOf(item)!!

        val userNum = "%.2f".format(args.amount.toDouble())

        b.userData.setText(args.date)
        b.userNumber.setText(userNum)
        b.userTime.setText(args.time)

        b.removeItem.setOnClickListener {
            viewModel.removeItem(item)
            dbRef.child("users").child(FirebaseAuth.getInstance().uid.toString()).child("waterList").setValue(viewModel.waterInfo)
            b.root.findNavController().navigateUp()
        }

        b.moveOnSon.setOnClickListener {
            val currentAmount = b.userNumber.text.toString().toDouble().toBigDecimal()
            val newNum = currentAmount.setScale(2, RoundingMode.HALF_EVEN)
            viewModel.replaceItem(index, WaterCupSon(b.userData.text.toString(), newNum.toDouble(), b.userTime.text.toString()))
            dbRef.child("users").child(FirebaseAuth.getInstance().uid.toString()).child("waterList").setValue(viewModel.waterInfo)
            b.root.findNavController().navigateUp()
        }

        return b.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}