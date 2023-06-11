package com.example.myapplicationb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Получаем ссылку на кнопку
        val registerButton = findViewById<Button>(R.id.login_activity_button)

        // Устанавливаем слушатель нажатий
        registerButton.setOnClickListener {
            val intentToAnotherScreen = Intent(this, RegistrationActivity::class.java)
            startActivity(intentToAnotherScreen)

            Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
        }

        // Получаем ссылку на текстовое поле
        val registerTextView = findViewById<TextView>(R.id.login_activity_text)

        // Устанавливаем слушатель нажатий
        registerTextView.setOnClickListener {
            val intentToAnotherScreen = Intent(this, LoginActivity::class.java)
            startActivity(intentToAnotherScreen)

            Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
        }
    }
}
