package com.pr656d.userslist.data.user

import com.pr656d.userslist.data.db.dao.UserDao
import com.pr656d.userslist.data.remote.NetworkService
import com.pr656d.userslist.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface UserRepository {
    suspend fun insertAll(userList: List<User>): List<Long>

    fun getAll(): Flow<List<User>>

    suspend fun loadFromDataSource()

    suspend fun clear()
}

class UserDataRepository @Inject constructor(
    private val userDao: UserDao,
    private val networkService: NetworkService
) : UserRepository {
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override suspend fun insertAll(userList: List<User>): List<Long> {
        return userDao.insertAll(userList)
    }

    override fun getAll(): Flow<List<User>> {
        // Start fetching data from data source.
        // Launch a new coroutine. We don't want to wait for response.
        scope.launch {
            loadFromDataSource()
        }
        return userDao.getAll()
    }

    override suspend fun loadFromDataSource() {
        val response = networkService.getUsers()
        // Clear the table.
        clear()
        // Add new data.
        response.results?.let {
            userDao.insertAll(it)
        }
    }

    override suspend fun clear() {
        userDao.clear()
    }
}