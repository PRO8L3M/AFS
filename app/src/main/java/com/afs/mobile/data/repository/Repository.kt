package com.afs.mobile.data.repository

import com.afs.mobile.data.database.AppDatabase
import com.afs.mobile.data.entity.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

class Repository(private val db: AppDatabase) {

    fun getTasks(): Flow<List<Task>> = db.tasksDao().getTasks().distinctUntilChanged()

    suspend fun insertTask(task: Task) = db.tasksDao().insertTask(task)

    suspend fun insertTasks(tasks: List<Task>) = db.tasksDao().insertTasks(tasks)

    suspend fun updateTask(task: Task) = db.tasksDao().updateTask(task)
}