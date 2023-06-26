package com.example.myapplicationb.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplicationb.R
import com.example.myapplicationb.data.User
import com.example.myapplicationb.viewmodel.MainActivityViewModel
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private val mMainActivityViewModel : MainActivityViewModel = MainActivityViewModel()

    private val signInLauncher = registerForActivityResult(    // создание объекта авторизации
        FirebaseAuthUIActivityResultContract()
    ) { resultCallback ->
        this.onSignInResult(resultCallback)        // запуск самого экрана
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openRegistrationScreen()
        // return                            ????????
    }

    /**
    * We make a call to firebase auth api to show dialog for registration
    */

    private fun openRegistrationScreen(){
        val intentToAnotherScreen = Intent(this, BooksActivity::class.java)
        startActivity(intentToAnotherScreen)

        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),     // список плагинов регистрации
            AuthUI.IdpConfig.PhoneBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.FacebookBuilder().build(),
            AuthUI.IdpConfig.TwitterBuilder().build(),
        )

        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()                                        // создали интента для экрана firebase
        signInLauncher.launch(signInIntent)                 // запуск экрана firebase auth
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        when (result.resultCode) {
            RESULT_OK -> {
                val authUser= FirebaseAuth.getInstance().currentUser   // создание объекта текущего пользователя firebase auth
                authUser?.let {             // если объект существует - сохраняем его в БД
                    val email = it.email.toString()         // извлекаем email пользователя
                    val uid = it.uid                        //  извлекаем uid
                    val firebaseUser = User(email, uid)            // создаем новый объект user  с параметрами email и uid

                    mMainActivityViewModel.updateUserData(firebaseUser, uid)

                    val intentToAnotherScreen = Intent(this, BooksActivity::class.java)
                    startActivity(intentToAnotherScreen)
                }
            }
            RESULT_CANCELED -> {
                Toast.makeText(this@MainActivity, "Something wrong with registration", Toast.LENGTH_SHORT).show()
            }
            else -> {
                // do not do anything
            }
        }
    }
}









//package com.example.myapplicationb
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.Button
//import android.widget.TextView
//import android.widget.Toast
//import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
//import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.ktx.database
//import com.google.firebase.ktx.Firebase
//
//class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        // Получаем ссылку на кнопку
//        val registerButton = findViewById<Button>(R.id.login_activity_button)
//
//        // Устанавливаем слушатель нажатий
//        registerButton.setOnClickListener {
//            val intentToAnotherScreen = Intent(this, RegistrationActivity::class.java)
//            startActivity(intentToAnotherScreen)
//
//            Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
//        }
//
//        // Получаем ссылку на текстовое поле
//        val registerTextView = findViewById<TextView>(R.id.login_activity_text)
//
//        // Устанавливаем слушатель нажатий
//        registerTextView.setOnClickListener {
//            val intentToAnotherScreen = Intent(this, LoginActivity::class.java)
//            startActivity(intentToAnotherScreen)
//
//            Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
//        }
//    }
//}


