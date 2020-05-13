package com.afs.mobile.data.repository

import com.afs.mobile.data.database.AppDatabase
import com.afs.mobile.data.entity.Task
import com.afs.mobile.data.entity.TaskState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

class TaskRepository(private val db: AppDatabase) {

    fun getTasks(): Flow<List<Task>> = db.tasksDao().getTasks().distinctUntilChanged()

    suspend fun insertTasks(tasks: List<Task>) = db.tasksDao().insertTasks(tasks)

    suspend fun updateTask(task: Task) = db.tasksDao().updateTask(task)

    fun getMockedTasks() = listOf(
        Task(name = "London", state = TaskState.OPEN),
        Task(name = "Chicago", state = TaskState.OPEN),
        Task(name = "Berlin", state = TaskState.OPEN),
        Task(name = "Rio", state = TaskState.OPEN),
        Task(name = "Los Angeles", state = TaskState.OPEN),
        Task(name = "Dubai", state = TaskState.OPEN),
        Task(name = "Wroclaw", state = TaskState.OPEN),
        Task(name = "Warszawa", state = TaskState.OPEN),
        Task(name = "Gdynia", state = TaskState.OPEN),
        Task(name = "Dortmund", state = TaskState.OPEN),
        Task(name = "Hamburg", state = TaskState.OPEN),
        Task(name = "Oslo", state = TaskState.OPEN),
        Task(name = "Otta", state = TaskState.OPEN),
        Task(name = "Lillehammer", state = TaskState.OPEN),
        Task(name = "Trondheim", state = TaskState.OPEN),
        Task(name = "Reykjavik", state = TaskState.OPEN),
        Task(name = "Vik i Myrdal", state = TaskState.OPEN),
        Task(name = "Antalya", state = TaskState.OPEN),
        Task(name = "Goteborg", state = TaskState.OPEN),
        Task(name = "Malmo", state = TaskState.OPEN),
        Task(name = "Rzym", state = TaskState.OPEN)
    )
}