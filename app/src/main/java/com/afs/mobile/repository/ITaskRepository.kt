package com.afs.mobile.repository

import com.afs.mobile.data.entity.Task
import com.afs.mobile.data.entity.Result
import kotlinx.coroutines.flow.Flow

interface ITaskRepository {
    suspend fun fetchTasks(): Result<List<Task>>
    suspend fun updateTask(task: Task): Result<Unit>
    fun getTasks(): Result<Flow<List<Task>>>
}