package com.pr656d.userslist.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.ANResponse
import com.pr656d.userslist.data.remote.EndPoints
import com.pr656d.userslist.data.user.datasource.UserEndPoints
import com.pr656d.userslist.data.user.datasource.response.UserResponse
import com.pr656d.userslist.utils.helper.DatabaseHelper
import com.pr656d.userslist.utils.helper.NetworkHelper

/**
 * The worker for refreshing user data.
 * What this worker does is
 *      - Fetch new data from API
 *      - Clear DB
 *      - Store data into local DB.
 */
class RefreshUsersWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    companion object {
        private const val USER_URL = EndPoints.BASE_URL + UserEndPoints.USERS
    }

    override suspend fun doWork(): Result {
        /**
         * Initialize networking, again.
         * Why again?
         *      Worker gets executed in background while application is not running.
         *      It's safe to reinitialize it so that we are sure that networking is up.
         */
        NetworkHelper.initialize(applicationContext)

        return try {
            val userDao = DatabaseHelper.getUserDao(applicationContext)

            val request = AndroidNetworking.get(USER_URL).build()
            val response = request.executeForObject(UserResponse::class.java) as? ANResponse<UserResponse>?

            if (response != null && !response.isSuccess) {
                return  Result.failure()
            }

            val users = response!!.result.results ?: return Result.retry()

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