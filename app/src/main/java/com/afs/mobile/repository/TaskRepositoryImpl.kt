package com.afs.mobile.repository

import com.afs.mobile.data.entity.Result
import com.afs.mobile.data.entity.Task
import com.afs.mobile.data.entity.doOnSuccess
import com.afs.mobile.data.entity.safeCall
import com.afs.mobile.data.localDataSource.LocalDataSource
import com.afs.mobile.data.remoteDataSource.RemoteDataSource
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : ITaskRepository {

    override suspend fun fetchTasks(): Result<List<Task>> =
        safeCall { remoteDataSource.fetchTask() }.doOnSuccess { localDataSource.saveTasks(it) }

    override suspend fun updateTask(task: Task): Result<Unit> =
        safeCall { localDataSource.updateTask(task) }

    override fun getTasks(): Result<Flow<List<Task>>> = safeCall { localDataSource.getTasks() }
}

