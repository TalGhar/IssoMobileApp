package com.example.issomobileapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
    }

    fun myButtonClickHandler(view: View?) {
        // Действия, выполняемые при нажатии на кнопку
    }
}