package com.example.myapplicationb

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("DEPRECATION")
class BooksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)
        // Choose authentication providers
        Log.d("testLogs", "RegistrationActivity start registration")

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = GridLayoutManager(this, 2)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(ItemsViewModel(R.drawable.admin_add_book_icon, "Item " + i))
        }

        val apiInterface = ApiInterface.create().getMovies("48a4ff9fa32412010f41d539955dc00c")

        //apiInterface.enqueue( Callback<List<Movie>>())
        apiInterface.enqueue( object : Callback<Movies>, CustomAdapter.ItemClickListener {
            override fun onResponse(call: Call<Movies>?, response: Response<Movies>?) {

                Log.d("testLogs", "OnResponse Success ${response?.body()?.results}")
                // This will pass the ArrayList to our Adapter
                val adapter = CustomAdapter(response?.body()?.results, this)

                // Setting the Adapter with the recyclerview
                recyclerview.adapter = adapter


                // Log.d("testLogs", "OnResponse Success ${call.toString()} ${response?.body()?.results}")
//                if(response?.body() != null)
//                    recyclerAdapter.setMovieListItems(response.body()!!)
            }

            override fun onFailure(call: Call<Movies>?, t: Throwable?) {
                Log.d("testLogs", "onFailure : ${t?.message}")
            }

            override fun onItemClick(position: Int) {
                Toast.makeText(this@BooksActivity, "click $position", Toast.LENGTH_SHORT).show()

            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finishAffinity()
        Log.d("testLogs", "RegistrationActivity Application been closed")
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
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.ktx.database
//import com.google.firebase.ktx.Firebase
//
//class RegistrationActivity : AppCompatActivity() {
//
//    private val signInLauncher = registerForActivityResult(    // создание объекта авторизации
//        FirebaseAuthUIActivityResultContract(),
//    ) { res ->
//        this.onSignInResult(res)        // запуск самого экрана
//    }
//
//
//    private lateinit var database: DatabaseReference         // Второй этап (создание объекта для записи в БД)
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_registration)
//
//        // Choose authentication providers
//        Log.d("testLogs", "RegistrationActivity start registration")
//        database = Firebase.database.reference          // инициализация базы данных
//
//        val providers = arrayListOf(
//            AuthUI.IdpConfig.EmailBuilder().build())     // список плагинов регистрации
//
//        // Create and launch sign-in intent
//        val signInIntent = AuthUI.getInstance()
//            .createSignInIntentBuilder()
//            .setAvailableProviders(providers)
//            .build()                                        // создали интента для экрана firebase
//        signInLauncher.launch(signInIntent)                 // запуск экрана firebase auth
//    }
//
//    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
//        val response = result.idpResponse        // результат с экрана firebase auth
//        if (result.resultCode == RESULT_OK) {                // проверка результата на OK
//            Log.d("testLogs", "RegistrationActivity registration success ${response?.email}")
//
//            // Successfully signed in
//            val authUser = FirebaseAuth.getInstance().currentUser   // создание объекта текущего пользователя firebase auth
//            authUser?.let {             // если объект существует - сохраняем его в БД
//                val email = it.email.toString()         // извлекаем email пользователя
//                val uid = it.uid                        //  извлекаем uid
//                val firebaseUser = User(email, uid)            // создаем новый объект user  с параметрами email и uid
//                Log.d("testLogs","RegistrationActivity firebaseUser $firebaseUser")
//                database.child("users").child(uid).setValue(firebaseUser)       // сохраняем пользователя в Firebase Realtime
//                onBackPressed()
//            }
//
//            // ...
//        } else {                  // обработка ошибки, если результат не OK
//            Log.d("testLogs", "RegistrationActivity registration failure")
//            // Sign in failed. If response is null the user canceled the
//            // sign-in flow using the back button. Otherwise check
//            // response.getError().getErrorCode() and handle the error.
//            // ...
//        }
//    }
//}












