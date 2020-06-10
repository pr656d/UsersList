package com.pr656d.userslist

import android.app.Application
import com.pr656d.userslist.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class UsersListApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(
                if (BuildConfig.DEBUG)
                    Level.DEBUG
                else
                    Level.NONE
            )
            androidContext(this@UsersListApplication)
            modules(appComponent)
        }
    }
}