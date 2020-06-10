package com.pr656d.userslist.di

import com.pr656d.userslist.ui.main.mainBindingModule
import org.koin.core.module.Module

/**
 * Contains all the activity level modules.
 */
@JvmField
val activityBindingModule: List<Module> = listOf(
    mainBindingModule
)