package com.pr656d.userslist.di

import com.pr656d.userslist.ui.MainActivity
import com.pr656d.userslist.ui.MainBindingModule
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