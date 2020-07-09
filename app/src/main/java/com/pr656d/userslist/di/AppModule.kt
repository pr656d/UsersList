package com.pr656d.userslist.di

import android.content.Context
import com.pr656d.userslist.UsersListApplication
import com.pr656d.userslist.data.db.AppDatabase
import com.pr656d.userslist.data.db.dao.UserDao
import com.pr656d.userslist.data.remote.EndPoints
import com.pr656d.userslist.data.remote.NetworkService
import com.pr656d.userslist.data.remote.Networking
import com.pr656d.userslist.data.user.UserDataRepository
import com.pr656d.userslist.data.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * Defines all the classes that need to be provided in the scope of the app.
 * If they are singleton mark them as '@Singleton'.
 */
@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = AppDatabase.buildDatabase(context)

    @Provides
    fun provideUserDao(appDatabase: AppDatabase) = appDatabase.userDao()

    @Singleton
    @Provides
    fun provideUserRepository(userDao: UserDao, networkService: NetworkService): UserRepository =
        UserDataRepository(userDao, networkService)

    @Singleton
    @Provides
    fun provideNetworkService(
        @ApplicationContext context: Context
    ): NetworkService =
        Networking.create(
            EndPoints.BASE_URL,
            context.cacheDir,
            10 * 1024 * 1024 // 10MB
        )
}
