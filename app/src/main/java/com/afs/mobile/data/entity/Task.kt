package com.afs.mobile.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.afs.mobile.database.TaskStateConverter

@TypeConverters(TaskStateConverter::class)
@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val name: String,
    val state: TaskState,
    val taskActivation: Boolean = true
)