package com.pr656d.userslist.data.user

import android.util.Log
import com.pr656d.userslist.data.db.dao.UserDao
import com.pr656d.userslist.data.remote.NetworkService
import com.pr656d.userslist.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

interface UserRepository {
    /**
     * Suspending which inserts the list of user into db.
     */
    suspend fun insertAll(userList: List<User>): List<Long>

    /**
     * Get all the users as Flow.
     */
    fun getAll(): Flow<List<User>>

    /**
     * 1. Load data from the data source.
     * 2. Clear the local data on response.
     * 3. Add new data into local database.
     */
    suspend fun updateData()

    /**
     * Clear the user data.
     */
    suspend fun clear()
}

@Singleton
class UserDataRepository @Inject constructor(
    private val userDao: UserDao,
    private val networkService: NetworkService
) : UserRepository {

    companion object {
        const val TAG = "UserDataRepository"
    }

    /**
     *  Coroutines launched in this scope will not cancel on one of them gets failed
     *  as we are using [SupervisorJob].
     */
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override suspend fun insertAll(userList: List<User>): List<Long> {
        return userDao.insertAll(userList)
    }

    override fun getAll(): Flow<List<User>> {
        // Start fetching data from data source.
        // Launch a new coroutine. We don't want to wait for response.
        scope.launch {
            try {
                // Error will be thrown also when internet is not available.
                // Try to fetch the data.
                updateData()
            } catch (e: Exception) {
                Log.d(TAG, "Could not update user data : ${e.message}")
            }
        }
        return userDao.getAll()
    }

    override suspend fun updateData() {
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