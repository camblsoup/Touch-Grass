package com.example.mytestapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.frontpage)  // Make sure this layout exists

        // Find the settings button by its ID
        val settingsButton: ImageButton = findViewById(R.id.settingsbutton)
        settingsButton.setOnClickListener {
            // Navigate to SettingsActivity
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        // Example: Handling a login button if you have one
        val loginButton: TextView = findViewById(R.id.login_button)
        loginButton.setOnClickListener {
            // Navigate to HomepageActivity (if implemented)
            val intent = Intent(this, HomepageActivity::class.java)
            startActivity(intent)
        }
    }
}
