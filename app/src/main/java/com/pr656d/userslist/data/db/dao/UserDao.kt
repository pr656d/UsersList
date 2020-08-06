package com.pr656d.userslist.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pr656d.userslist.model.User
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for the [User] class.
 */
@Dao
interface UserDao {
    /**
     * Insert the list of user data.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(userList: List<User>): List<Long>

    /**
     * Get all user list.
     */
    @Query("SELECT * FROM userList")
    fun getAll(): List<User>

    /**
     * Clears the table data.
     */
    @Query("DELETE FROM userList")
    fun clear()
}