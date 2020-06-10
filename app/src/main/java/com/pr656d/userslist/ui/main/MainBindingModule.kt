package com.pr656d.userslist.ui.main

import org.koin.androidx.experimental.dsl.viewModel
import org.koin.dsl.module

/**
 * Module where classes needed for main activity launch are defined.
 */
@JvmField
val mainBindingModule = module {
    viewModel<MainViewModel>()
}