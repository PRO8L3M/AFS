package com.afs.mobile.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.afs.mobile.common.DATABASE
import com.afs.mobile.data.entity.Task

@Database(
    entities = [Task::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun tasksDao(): TaskDao

    companion object {
        fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, DATABASE).build()
    }
}