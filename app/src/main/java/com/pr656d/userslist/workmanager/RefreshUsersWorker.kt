package com.pr656d.userslist.workmanager

import android.content.Context
import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.pr656d.userslist.data.db.dao.UserDao
import com.pr656d.userslist.data.remote.NetworkService

class RefreshUsersWorker @WorkerInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val userDao: UserDao,
    private val networkService: NetworkService
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        return try {
            val users = networkService.getUsers().results ?: let {
                Log.d(javaClass.simpleName, "No user found")
                return Result.retry()
            }

            // Clear the data
            userDao.clear()

            // Add data
            userDao.insertAll(users)

            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}