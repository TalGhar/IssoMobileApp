package com.example.issomobileapp

import android.app.Application
import android.content.Intent
import com.parse.Parse;
import com.parse.ParseUser

class App : Application() {
    //region Override Functions
    override fun onCreate() {
        super.onCreate()
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build());

    }
    //endregion
}