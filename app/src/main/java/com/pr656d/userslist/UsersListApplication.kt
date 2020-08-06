package com.pr656d.userslist

import android.app.Application
import android.util.Log
import androidx.work.Configuration
import com.pr656d.userslist.utils.helper.NetworkHelper

class UsersListApplication : Application(), Configuration.Provider {
    override fun onCreate() {
        super.onCreate()
        setupNetworking()
    }

    private fun setupNetworking() {
        NetworkHelper.initialize(this)
    }

    override fun getWorkManagerConfiguration(): Configuration = Configuration.Builder()
        .setMinimumLoggingLevel(
            if (BuildConfig.DEBUG) Log.DEBUG else Log.INFO
        )
        .build()
}