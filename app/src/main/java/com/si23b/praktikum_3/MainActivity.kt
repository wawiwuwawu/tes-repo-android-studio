package com.si23b.praktikum_3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        if (!sharedPreferences.getBoolean("isLoggedIn", false)) {
            startActivity(
                Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        setContentView(R.layout.activity_main)
        val username = sharedPreferences.getString("loggedInUser", "User")
        findViewById<TextView>(R.id.TextView).text = "Welcome, $username!"

        val logoutButton = findViewById<Button>(R.id.ButtonLogout)
        logoutButton.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.putBoolean("isLoggedIn", false)
            editor.remove("loggedInUser")
            editor.apply()

            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }
}