package com.example.mytestapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class HomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // homepage
        setContentView(R.layout.homepage)
        // declare button
        val settingsButton: ImageButton = findViewById(R.id.settingsbutton)

        // onclick,
        settingsButton.setOnClickListener {
            // go to settings
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }
}
