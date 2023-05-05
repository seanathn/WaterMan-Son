package com.example.waterman_son

import android.app.appsearch.AppSearchResult.RESULT_OK
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.navigation.findNavController
import com.example.waterman_son.databinding.FragmentLogInSonBinding
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LogInSonFragment : Fragment() {
    private var _binding : FragmentLogInSonBinding? = null
    private val b get() = _binding!!
    lateinit var dbRef : DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLogInSonBinding.inflate(inflater, container,false)

        dbRef = Firebase.database.reference

        b.movinAndGroovin.setOnClickListener {
            val action = LogInSonFragmentDirections.actionLogInSonFragmentToWatermanSonMainFragment()
            b.root.findNavController().navigate(action)

            val signInLauncher = registerForActivityResult(
                FirebaseAuthUIActivityResultContract(),
            ) { res ->
                this.onSignInResult(res)
            }

            val actionCodeSettings = ActionCodeSettings.newBuilder()
                .setAndroidPackageName( // yourPackageName=
                    "com.example.waterman_son", // installIfNotAvailable=
                    true, // minimumVersion=
                    null,
                )
                .setHandleCodeInApp(true) // This must be set to true
                .setUrl("https://google.com") // This URL needs to be whitelisted
                .build()

            val providers = listOf(
                AuthUI.IdpConfig.EmailBuilder()
                    .enableEmailLinkSignIn()
                    .setActionCodeSettings(actionCodeSettings)
                    .build(),
            )
            val signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build()
            signInLauncher.launch(signInIntent)

//            var googleLogInStuff = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
//            dbRef.child("username").push().setValue(b.userName.text.toString())
//            dbRef.child("password").push().setValue(b.userPassword.text.toString())
        }

        return b.root
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            // Successfully signed in
            val user = FirebaseAuth.getInstance().currentUser
            // ...
        } else {
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}