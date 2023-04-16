package com.example.issomobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class SignInActivity : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var password: EditText

    private lateinit var signInButton: Button

    private lateinit var stillNot: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        username = findViewById(R.id.editTexLoginUsername)
        password = findViewById(R.id.editTextLoginPassword)

        signInButton = findViewById(R.id.signInButton)

        stillNot = findViewById(R.id.stillNot)

        stillNot.setOnClickListener {
            stillNot()
        }

    }

    private fun stillNot() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

}