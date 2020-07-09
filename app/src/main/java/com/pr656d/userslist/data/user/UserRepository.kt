package com.pr656d.userslist.data.user

import android.util.Log
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.pr656d.userslist.data.db.dao.UserDao
import com.pr656d.userslist.data.remote.NetworkService
import com.pr656d.userslist.model.User
import com.pr656d.userslist.workmanager.RefreshUsersWorker
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
     * Schedule a WorkManager worker to update data.
     */
    fun scheduleUpdateDataWorker()

    /**
     * Clear the user data.
     */
    suspend fun clear()
}

@Singleton
class UserDataRepository @Inject constructor(
    private val userDao: UserDao,
    private val networkService: NetworkService,
    private val workManager: WorkManager
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
        // Schedule a worker. We don't want to wait for response.
        scheduleUpdateDataWorker()
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

    override fun scheduleUpdateDataWorker() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest = OneTimeWorkRequestBuilder<RefreshUsersWorker>()
            .setConstraints(constraints)
            .build()

        workManager.enqueue(workRequest)
    }

    override suspend fun clear() {
        userDao.clear()
    }
}