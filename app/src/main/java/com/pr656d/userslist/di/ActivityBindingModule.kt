package com.pr656d.userslist.di

import com.pr656d.userslist.ui.main.MainActivity
import com.pr656d.userslist.ui.main.MainBindingModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("UNUSED")
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [MainBindingModule::class]
    )
    internal abstract fun mainActivity(): MainActivity
}