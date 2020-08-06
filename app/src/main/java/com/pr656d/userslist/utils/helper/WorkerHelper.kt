package com.pr656d.userslist.utils.helper

import android.content.Context
import androidx.work.*
import com.pr656d.userslist.workmanager.RefreshUsersWorker
import java.util.*

/**
 * Helper for [WorkManager].
 */
object WorkerHelper {
    // Unique worker name
    private const val REFRESH_USERS_WORKER = "com.pr656d.userslist.refreshusersworker"

    /**
     * Schedule a worker to refresh users data.
     * @param context Context
     * @return [UUID] Id of the work request.
     */
    fun refreshUsers(context: Context): UUID {
        // Set constraints for enabled network.
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        // Create work request
        val workRequest = OneTimeWorkRequestBuilder<RefreshUsersWorker>()
            .setConstraints(constraints)
            .build()

        // Schedule unique work.
        WorkManager.getInstance(context)
            .enqueueUniqueWork(REFRESH_USERS_WORKER, ExistingWorkPolicy.REPLACE, workRequest)

        return workRequest.id
    }
}