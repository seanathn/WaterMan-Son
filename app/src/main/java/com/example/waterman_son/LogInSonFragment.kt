package com.example.waterman_son

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.waterman_son.databinding.FragmentLogInSonBinding
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LogInSonFragment : Fragment() {
    private var _binding : FragmentLogInSonBinding? = null
    private val b get() = _binding!!
    lateinit var dbRef : DatabaseReference
    private lateinit var auth: FirebaseAuth
    private val viewModel: WaterManSonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLogInSonBinding.inflate(inflater, container,false)

        dbRef = Firebase.database.reference

        auth = FirebaseAuth.getInstance()

        b.movinAndGroovin.setOnClickListener {
            signIn(b.userName.text.toString(), b.userPassword.text.toString())
        }

        return b.root
    }

    private fun createAccount(email: String, password: String) {
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    Firebase.database.getReference("users/" + user!!.uid + "/waterList").setValue(viewModel.waterInfo)
                    val action = LogInSonFragmentDirections.actionLogInSonFragmentToWatermanSonMainFragment()
                    b.root.findNavController().navigate(action)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        context,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
//                    updateUI(null)
                }
            }
        // [END create_user_with_email]
    }

    private fun signIn(email: String, password: String) {
        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    Firebase.database.getReference("users/" + user!!.uid + "/waterList").setValue(viewModel.waterInfo)
                    val action = LogInSonFragmentDirections.actionLogInSonFragmentToWatermanSonMainFragment()
                    b.root.findNavController().navigate(action)
//                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    MaterialAlertDialogBuilder(requireContext()).setTitle("The information does not exist yet. Would you like to add it?")
                        .setMessage("If the email is being used double check your password").setPositiveButton("Yes"){ dialog, which ->
                            createAccount(email, password)
                        }.setNegativeButton("No"){dialog, which ->

                        }.show()
//                    updateUI(null)
                }
            }
        // [END sign_in_with_email]
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}