package com.example.mytestapplication

package com.example.mytestapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {

    // Creating constant keys for shared preferences.
    companion object {
        const val SHARED_PREFS = "shared_prefs"
        const val FIRST_NAME_KEY = "first_name_key"
        const val EMAIL_KEY = "email_key"
        const val PASSWORD_KEY = "password_key"
    }

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Initialize UI components
        val firstNameInput = findViewById<EditText>(R.id.firstNameInput)
        val emailInput = findViewById<EditText>(R.id.emailInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)
        val signUpButton = findViewById<Button>(R.id.signUpButton)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)

        // Handle Sign-Up button click
        signUpButton.setOnClickListener {
            val firstName = firstNameInput.text.toString()
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if (firstName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                // Store user details in SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putString(FIRST_NAME_KEY, firstName)
                editor.putString(EMAIL_KEY, email)
                editor.putString(PASSWORD_KEY, password)
                editor.apply()

                Toast.makeText(this, "Sign-Up Successful", Toast.LENGTH_SHORT).show()

                // Navigate to Login screen
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
                finish()  // Close sign-up activity
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
