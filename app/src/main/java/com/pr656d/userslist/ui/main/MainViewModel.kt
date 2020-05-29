package com.pr656d.userslist.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.pr656d.userslist.data.user.UserRepository
import com.pr656d.userslist.model.User
import javax.inject.Inject

/**
 * For simplicity using [UserRepository] directly.
 * We should add one more layer between [ViewModel] and [UserRepository]
 * which executes tasks in background.
 */
class MainViewModel @Inject constructor(
    repository: UserRepository
) : ViewModel() {

    val users: LiveData<List<User>> = repository.getAll().asLiveData()

}