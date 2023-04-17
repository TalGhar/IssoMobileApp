package com.example.issomobileapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.Patterns
import android.view.MotionEvent
import android.view.Window
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.parse.ParseUser;
import com.parse.SignUpCallback;

class SignUpActivity : AppCompatActivity() {

    //region Private Properties
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var repeatPassword: EditText
    private lateinit var username: EditText

    private lateinit var signUpButton: Button
    private lateinit var alreadyHaveButton: TextView

    private lateinit var emailError: TextView
    private lateinit var passwordError: TextView
    private lateinit var repeatPasswordError: TextView
    private lateinit var usernameError: TextView
    //endregion

    //region Override Functions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.activity_sign_up)

        email = findViewById(R.id.editTextEmail)
        password = findViewById(R.id.editTextPassword)
        repeatPassword = findViewById(R.id.editTextRepeatPassword)
        username = findViewById(R.id.editTextUsername)

        signUpButton = findViewById(R.id.signUpButton)
        alreadyHaveButton = findViewById(R.id.alreadyHave)

        emailError = findViewById(R.id.emailError)
        passwordError = findViewById(R.id.passwordError)
        repeatPasswordError = findViewById(R.id.repeatPasswordError)
        usernameError = findViewById(R.id.usernameError)

        signUpButton.setOnClickListener {
            signUp(email.text.toString(), password.text.toString(), repeatPassword.text.toString(), username.text.toString())
        }

        alreadyHaveButton.setOnClickListener {
            alreadyHave()
        }

    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev != null) {
            if (ev.action == MotionEvent.ACTION_DOWN) hideKeyBoard()
        }
        return super.dispatchTouchEvent(ev)
    }
    //endregion

    //region Private Functions
    private fun hideKeyBoard() {
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(window.decorView.windowToken, 0)
    }

    private fun signUp(email: String, password: String, repeatPassword: String, username: String) {

        val user = ParseUser()

        if (validate(this.emailError, this.passwordError, this.repeatPasswordError, this.usernameError, email, password, repeatPassword, username)) {
            user.email = email
            user.username = username
            user.setPassword(password)
            user.signUpInBackground(SignUpCallback {
                if (it == null) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    ParseUser.logOut()
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            })
        }

    }

    private fun alreadyHave() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidUsername(username: String): Boolean {
        return !TextUtils.isEmpty(username) && username.length > 6
    }

    private fun isValidPassword(password: String): Boolean {

        if (password.length < 8) return false
        if (password.firstOrNull { it.isDigit() } == null) return false
        if (password.filter { it.isLetter() }.firstOrNull { it.isUpperCase() } == null) return false
        if (password.filter { it.isLetter() }.firstOrNull { it.isLowerCase() } == null) return false
        if (password.firstOrNull { !it.isLetterOrDigit() } == null) return false

        return true
    }
    private fun validate(emailError: TextView, passwordError: TextView, repeatPasswordError: TextView, usernameError: TextView, email: String, password: String, repeatPassword: String, username: String): Boolean {

        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)

        if (!isValidEmail(email)) {
            emailError.text = getString(R.string.email_error)
            emailError.startAnimation(fadeIn)
            emailError.setTextColor(resources.getColor(R.color.teal_700))
        } else {
            emailError.startAnimation(fadeOut)
            Handler().postDelayed({
                emailError.text = ""
            }, 600)
        }

        if (!isValidPassword(password)) {
            passwordError.text = getString(R.string.password_error)
            passwordError.startAnimation(fadeIn)
            passwordError.setTextColor(resources.getColor(R.color.teal_700))
        } else {
            passwordError.startAnimation(fadeOut)
            Handler().postDelayed({
                passwordError.text = ""
            }, 600)
        }

        if (password != repeatPassword) {
            repeatPasswordError.text = getString(R.string.repeat_password_error)
            repeatPasswordError.startAnimation(fadeIn)
            repeatPasswordError.setTextColor(resources.getColor(R.color.teal_700))
        } else {
            repeatPasswordError.startAnimation(fadeOut)
            Handler().postDelayed({
                repeatPasswordError.text = ""
            }, 600)
        }

        if (!isValidUsername(username)) {
            usernameError.text = getString(R.string.username_error)
            usernameError.startAnimation(fadeIn)
            usernameError.setTextColor(resources.getColor(R.color.teal_700))
        } else {
            usernameError.startAnimation(fadeOut)
            Handler().postDelayed({
                usernameError.text = ""
            }, 600)
        }

        return isValidEmail(email) && isValidPassword(password) && password == repeatPassword && isValidUsername(username)
    }
    //endregion


}