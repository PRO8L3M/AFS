package com.afs.mobile.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.afs.mobile.common.DATABASE
import com.afs.mobile.data.entity.Task

@Database(
    entities = [Task::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun tasksDao(): TasksDao

    companion object {
        fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, DATABASE).build()
    }
}