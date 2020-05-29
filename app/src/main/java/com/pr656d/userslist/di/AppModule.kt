package com.pr656d.userslist.di

import android.content.Context
import com.pr656d.userslist.UsersListApplication
import dagger.Module
import dagger.Provides

/**
 * Defines all the classes that need to be provided in the scope of the app.
 * If they are singleton mark them as '@Singleton'.
 */
@Module
class AppModule {
    @Provides
    fun provideContext(application: UsersListApplication): Context = application.applicationContext
}
