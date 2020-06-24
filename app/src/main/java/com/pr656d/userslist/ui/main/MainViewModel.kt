package com.pr656d.userslist.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.pr656d.userslist.data.user.UserRepository
import com.pr656d.userslist.model.User

/**
 * For simplicity using [UserRepository] directly.
 * We should add one more layer between [ViewModel] and [UserRepository]
 * which executes tasks in background.
 */
class MainViewModel @ViewModelInject constructor(
    repository: UserRepository
) : ViewModel() {

    val users: LiveData<List<User>> = repository.getAll().asLiveData()

    val isEmpty
        get() = users.map { it.isNullOrEmpty() }

}