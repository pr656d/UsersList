package com.pr656d.userslist.di

import com.pr656d.userslist.data.db.AppDatabase
import com.pr656d.userslist.data.remote.EndPoints
import com.pr656d.userslist.data.remote.Networking
import com.pr656d.userslist.data.user.UserDataRepository
import com.pr656d.userslist.data.user.UserRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.experimental.builder.singleBy

/**
 * Defines all the classes that need to be provided in the scope of the app.
 */
@JvmField
val appModule = module {
    single { AppDatabase.buildDatabase(get()) }

    factory { get<AppDatabase>().userDao() }

    singleBy<UserRepository, UserDataRepository>()

    single {
        Networking.create(
            EndPoints.BASE_URL,
            androidContext().cacheDir,
            10 * 1024 * 1024 // 10MB
        )
    }
}
