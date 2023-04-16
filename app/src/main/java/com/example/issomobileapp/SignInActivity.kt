package com.example.issomobileapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.parse.ParseException
import com.parse.ParseUser

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

        signInButton.setOnClickListener {
            signIn(username.text.toString(), password.text.toString())
        }

    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev != null) {
            if (ev.action == MotionEvent.ACTION_DOWN) hideKeyBoard()
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun hideKeyBoard() {
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(window.decorView.windowToken, 0)
    }


    private fun stillNot() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun signIn(username: String, password: String) {

        ParseUser.logInInBackground(username, password) { parseUser: ParseUser?, parseException: ParseException? ->
            if (parseUser != null) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                ParseUser.logOut()
                if (parseException != null) {
                    Toast.makeText(this,parseException.message, Toast.LENGTH_LONG).show()
                }
            }
        }

    }

}