package com.pr656d.userslist.di

import com.pr656d.userslist.UsersListApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Main component of the app, created and persisted in the Application level.
 */
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBindingModule::class,
        AppModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<UsersListApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: UsersListApplication): AppComponent
    }
}