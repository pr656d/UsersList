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
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(userList: List<User>): List<Long>

    @Query("SELECT * FROM userList")
    fun getAll(): Flow<List<User>>

    @Query("DELETE FROM userList")
    suspend fun clear()
}