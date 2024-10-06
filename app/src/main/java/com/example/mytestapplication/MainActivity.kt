package com.example.mytestapplication

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // go to frontpage
        setContentView(R.layout.frontpage)
        val loginButton: TextView = findViewById(R.id.login_button)

        // clicking start
        loginButton.setOnClickListener {
            // navigate to homepage
            val intent = Intent(this, HomePageActivity::class.java)
            startActivity(intent)
        }
    }
}
