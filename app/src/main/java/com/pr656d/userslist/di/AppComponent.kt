package com.pr656d.userslist.di

import org.koin.core.module.Module

/**
 * Main component of the app, created and persisted in the Application level.
 * Consists all the modules of the app.
 */
val appComponent: List<Module> = appModule + activityBindingModule