package com.example.mytestapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Login : AppCompatActivity() {

    // creating constant keys for shared preferences.
    companion object {
        const val SHARED_PREFS = "shared_prefs"
        const val EMAIL_KEY = "email_key"
        const val PASSWORD_KEY = "password_key"
    }

    // variable for shared preferences.
    private lateinit var sharedpreferences: SharedPreferences
    private var email: String? = null
    private var password: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        // Initializing EditTexts and our Button
        val emailEdt = findViewById<EditText>(R.id.idEdtEmail)
        val passwordEdt = findViewById<EditText>(R.id.idEdtPassword)
        val loginBtn = findViewById<Button>(R.id.idBtnLogin)

        // getting the data which is stored in shared preferences.
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)

        // in shared prefs inside get string method
        // we are passing key value as EMAIL_KEY and
        // default value is
        // set to null if not present.
        email = sharedpreferences.getString("EMAIL_KEY", null)
        password = sharedpreferences.getString("PASSWORD_KEY", null)

        // calling on click listener for login button.
        loginBtn.setOnClickListener {
            // to check if the user fields are empty or not.
            if (TextUtils.isEmpty(emailEdt.text.toString()) && TextUtils.isEmpty(passwordEdt.text.toString())) {
                // this method will call when email and password fields are empty.
                Toast.makeText(this@Login, "Please Enter Email and Password", Toast.LENGTH_SHORT).show()
            } else {
                val editor = sharedpreferences.edit()

                // below two lines will put values for
                // email and password in shared preferences.
                editor.putString(EMAIL_KEY, emailEdt.text.toString())
                editor.putString(PASSWORD_KEY, passwordEdt.text.toString())

                // to save our data with key and value.
                editor.apply()

                // starting new activity.
                val i = Intent(this@Login, HomeActivity::class.java)
                startActivity(i)
                finish()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (email != null && password != null) {
            val i = Intent(this@Login, HomeActivity::class.java)
            startActivity(i)
        }
    }
}
