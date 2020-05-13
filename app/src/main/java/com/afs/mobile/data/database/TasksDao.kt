package com.afs.mobile.data.database

import androidx.room.*
import com.afs.mobile.data.entity.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

    @Insert
    suspend fun insertTask(task: Task)

    @Insert
    suspend fun insertTasks(list: List<Task>)

    @Update
    suspend fun updateTask(task: Task)

    @Query("SELECT * FROM Task")
    fun getTasks(): Flow<List<Task>>
}