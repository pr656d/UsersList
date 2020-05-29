package com.pr656d.userslist.fakes

import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.pr656d.userslist.data.db.AppDatabase
import com.pr656d.userslist.data.db.dao.UserDao
import org.mockito.Mockito

class FakeAppDatabase : AppDatabase() {
    override fun userDao(): UserDao {
        return Mockito.mock(UserDao::class.java)
    }

    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        return Mockito.mock(SupportSQLiteOpenHelper::class.java)
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        return Mockito.mock(InvalidationTracker::class.java)
    }

    override fun clearAllTables() {}
}