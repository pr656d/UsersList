package com.pr656d.userslist.data.user

import com.pr656d.userslist.data.dao.UserDao
import com.pr656d.userslist.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface UserRepository {
    suspend fun insertAll(userList: List<User>): List<Long>

    fun getAll(): Flow<List<User>>

    suspend fun clear()
}

class UserDataRepository @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun insertAll(userList: List<User>): List<Long> {
        return userDao.insertAll(userList)
    }

    override fun getAll(): Flow<List<User>> = userDao.getAll()

    override suspend fun clear() {
        userDao.clear()
    }
}