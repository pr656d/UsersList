package com.pr656d.userslist

import com.pr656d.userslist.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class UsersListApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}