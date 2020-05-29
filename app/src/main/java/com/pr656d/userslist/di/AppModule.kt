package com.pr656d.userslist.di

import android.content.Context
import com.pr656d.userslist.UsersListApplication
import com.pr656d.userslist.data.db.AppDatabase
import com.pr656d.userslist.data.remote.EndPoints
import com.pr656d.userslist.data.remote.NetworkService
import com.pr656d.userslist.data.remote.Networking
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Defines all the classes that need to be provided in the scope of the app.
 * If they are singleton mark them as '@Singleton'.
 */
@Module
class AppModule {
    @Provides
    fun provideContext(application: UsersListApplication): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideDatabase(context: Context) = AppDatabase.buildDatabase(context)

    @Provides
    fun provideUserDao(appDatabase: AppDatabase) = appDatabase.userDao()

    @Provides
    @Singleton
    fun provideNetworkService(
        application: UsersListApplication
    ): NetworkService =
        Networking.create(
            EndPoints.BASE_URL,
            application.cacheDir,
            10 * 1024 * 1024 // 10MB
        )
}
