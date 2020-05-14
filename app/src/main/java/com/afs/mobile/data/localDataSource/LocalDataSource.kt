package com.afs.mobile.data.localDataSource

import com.afs.mobile.data.entity.Task
import com.afs.mobile.database.AppDatabase

class LocalDataSource(private val db: AppDatabase) {
    suspend fun saveTasks(tasks: List<Task>) = db.tasksDao().insertTasks(tasks)
    suspend fun updateTask(task: Task) = db.tasksDao().updateTask(task)
    fun getTasks() = db.tasksDao().getTasks()
}
