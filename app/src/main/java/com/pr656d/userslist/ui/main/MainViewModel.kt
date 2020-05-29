package com.pr656d.userslist.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.pr656d.userslist.data.user.UserRepository
import com.pr656d.userslist.model.User
import javax.inject.Inject

class MainViewModel @Inject constructor(
    repository: UserRepository
) : ViewModel() {

    val users: LiveData<List<User>> = repository.getAll().asLiveData()

}