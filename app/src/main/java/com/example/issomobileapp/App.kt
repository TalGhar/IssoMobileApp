package com.example.issomobileapp

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatDelegate
import com.parse.Parse;
import com.parse.ParseUser

class App : Application() {
    //region Override Functions
    override fun onCreate() {
        super.onCreate()
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId("X5QzIDrxOW4H5zGadS4u5pBKOtO1Xz3Lt5A8yshg")
                .clientKey("hzDGYWWyhXEU6gZerOkrHcGmWNmfgaSamV0CbNIS")
                .server(getString(R.string.back4app_server_url))
                .build()
        );

    }
    //endregion
}