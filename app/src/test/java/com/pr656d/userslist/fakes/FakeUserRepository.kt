package com.pr656d.userslist.fakes

import com.pr656d.userslist.data.db.AppDatabase
import com.pr656d.userslist.data.user.UserRepository
import com.pr656d.userslist.model.User
import kotlinx.coroutines.flow.Flow

open class FakeUserRepository(
    private val fakeAppDatabase: AppDatabase = FakeAppDatabase()
) : UserRepository {

    override suspend fun insertAll(userList: List<User>): List<Long> {
        return fakeAppDatabase.userDao().insertAll(userList)
    }

    override fun getAll(): Flow<List<User>> {
        return fakeAppDatabase.userDao().getAll()
    }

    override suspend fun loadFromDataSource() { }

    override suspend fun clear() {}
}