package com.pr656d.userslist.utils.helper

import android.content.Context
import com.pr656d.userslist.data.db.AppDatabase
import com.pr656d.userslist.data.db.dao.UserDao

/**
 * Helper for database.
 */
object DatabaseHelper {

    /**
     * Get the database.
     * @return [AppDatabase] instance.
     */
    fun getDatabase(context: Context): AppDatabase = AppDatabase.getInstance(context)

    /**
     * @param context [Context]
     * @return [UserDao]
     */
    fun getUserDao(context: Context): UserDao = getDatabase(
        context
    ).userDao()
}