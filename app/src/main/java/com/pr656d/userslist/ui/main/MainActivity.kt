package com.pr656d.userslist.ui.main

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.pr656d.userslist.R
import com.pr656d.userslist.data.db.dao.UserDao
import com.pr656d.userslist.utils.helper.DatabaseHelper
import com.pr656d.userslist.utils.helper.WorkerHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val usersRecyclerView by lazy { findViewById<RecyclerView>(R.id.rvUserList) }
    private val emptyListMessage by lazy { findViewById<TextView>(R.id.emptyListMessage) }
    private val progress by lazy { findViewById<ProgressBar>(R.id.progress) }

    private val userListAdapter by lazy { UserListAdapter() }

    private val userDao: UserDao by lazy { DatabaseHelper.getUserDao(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()

        // Update UI with the existing data.
        updateUI()

        val refreshUsersWorkerID = WorkerHelper.refreshUsers(this)

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(refreshUsersWorkerID)
            .observe(this, Observer {
                it ?: return@Observer

                when (it.state) {
                    WorkInfo.State.SUCCEEDED -> {
                        updateUI()
                    }
                    WorkInfo.State.RUNNING -> {
                        progress.isVisible = true
                    }
                    WorkInfo.State.ENQUEUED -> {
                        /* no-op */
                    }
                    WorkInfo.State.FAILED -> {
                        Toast.makeText(this, R.string.error_load, Toast.LENGTH_LONG).show()
                    }
                    WorkInfo.State.BLOCKED -> {
                        /* no-op */
                    }
                    WorkInfo.State.CANCELLED -> {
                        /* no-op */
                    }
                }
            })
    }

    private fun initRecyclerView() {
        usersRecyclerView.adapter = userListAdapter
    }

    /**
     * Fetch new data from the database and update the UI accordingly.
     */
    private fun updateUI() {
        progress.isVisible = true

        CoroutineScope(Dispatchers.IO).launch {
            val users = userDao.getAll()

            withContext(Dispatchers.Main) {
                if (users.isEmpty()) {
                    usersRecyclerView.isVisible = false
                    emptyListMessage.isVisible = true
                    progress.isVisible = false
                } else {
                    emptyListMessage.isVisible = false
                    progress.isVisible = false
                }

                userListAdapter.submitList(users)
            }
        }
    }
}