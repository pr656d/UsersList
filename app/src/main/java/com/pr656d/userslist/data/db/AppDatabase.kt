package com.pr656d.userslist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pr656d.userslist.data.db.dao.UserDao
import com.pr656d.userslist.model.User

/**
 * The [Room] database for this app.
 */
@Database(
    entities = [
        User::class
    ],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private const val databaseName = "userlist-db"

        @Volatile
        private var instance: AppDatabase? = null

        /**
         * Returns singleton instance of the database if exists.
         * If not exist then creates a new instance.
         * @return Singleton instance of the database.
         */
        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        /**
         * Build the database.
         * As we clear and store new data every time app launches destructive migration is fine
         * in our use case.
         *
         * @param context [Context]
         * @return [AppDatabase]
         */
        private fun buildDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, databaseName)
                .fallbackToDestructiveMigration()
                .build()
    }
}