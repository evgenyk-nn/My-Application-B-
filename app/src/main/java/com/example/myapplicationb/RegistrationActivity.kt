package com.example.myapplicationb

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth

class RegistrationActivity : AppCompatActivity() {

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract(),
    ) { res ->
        this.onSignInResult(res)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        // Choose authentication providers
        Log.d("testLogs", "RegistrationActivity start registration")
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build())

        // Create and launch sign-in intent
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        signInLauncher.launch(signInIntent)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            Log.d("testLogs", "RegistrationActivity registration success ${response?.email}")
            val user = FirebaseAuth.getInstance().currentUser
            // ...
        } else {
            Log.d("testLogs", "RegistrationActivity registration failure")
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }
}




//package com.example.myapplicationb
//
//import android.os.Bundle
//import android.util.Log
//import androidx.appcompat.app.AppCompatActivity
//import com.firebase.ui.auth.AuthUI
//import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
//import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
//import com.google.firebase.auth.FirebaseAuth
//
//class RegistrationActivity : AppCompatActivity() {
//
//    private val signUpLauncher = registerForActivityResult(
//        FirebaseAuthUIActivityResultContract(),
//    ) { res ->
//        this.onSignUpResult(res)
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_registration)
//
//        // Choose authentication providers
//        Log.d("testLogs", "RegistrationActivity start registration")
//        val providers = arrayListOf(
//            AuthUI.IdpConfig.EmailBuilder().build(),
//            AuthUI.IdpConfig.PhoneBuilder().build(),
//            AuthUI.IdpConfig.GoogleBuilder().build(),
//            AuthUI.IdpConfig.FacebookBuilder().build(),
//            AuthUI.IdpConfig.TwitterBuilder().build(),
//        )
//
//        // Create and launch sign-up intent
//        val signUpIntent = AuthUI.getInstance()
//            .createSignInIntentBuilder() // Исправлено на createSignInIntentBuilder()
//            .setAvailableProviders(providers)
//            .build()
//        signUpLauncher.launch(signUpIntent)
//    }
//
//    private fun onSignUpResult(result: FirebaseAuthUIAuthenticationResult) {
//        val response = result.idpResponse
//        if (result.resultCode == RESULT_OK) {
//            Log.d("testLogs", "RegistrationActivity registration success ${response?.email}")
//            val user = FirebaseAuth.getInstance().currentUser
//            // ...
//        } else {
//            Log.d("testLogs", "RegistrationActivity registration failure")
//            // Sign-up failed. If response is null, the user canceled the
//            // sign-up flow using the back button. Otherwise, check
//            // response.getError().getErrorCode() and handle the error.
//            // ...
//        }
//    }
//}







