package com.afs.mobile.repository

import com.afs.mobile.data.entity.Result
import com.afs.mobile.data.entity.Task
import com.afs.mobile.data.entity.doOnSuccess
import com.afs.mobile.data.entity.safeCall
import com.afs.mobile.data.localDataSource.LocalDataSource
import com.afs.mobile.data.remoteDataSource.RemoteDataSource
import com.afs.mobile.database.AppDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class TaskRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : ITaskRepository {

    override suspend fun fetchTasks(): Result<List<Task>> =
        safeCall { remoteDataSource.fetchTask() }.doOnSuccess { localDataSource.saveTasks(it) }

    override suspend fun updateTask(task: Task): Result<Unit> =
        safeCall { localDataSource.updateTask(task) }

    override fun getTasks(): Result<Flow<List<Task>>> = safeCall { localDataSource.getTasks() }

}

