package com.afs.mobile.database

import androidx.room.TypeConverter
import com.afs.mobile.data.entity.TaskState

class TaskStateConverter {

    @TypeConverter
    fun fromTaskState(value: TaskState) = value.name

    @TypeConverter
    fun toTaskState(value: String) = when (value) {
        "OPEN" -> TaskState.OPEN
        "TRAVELLING" -> TaskState.TRAVELLING
        else -> TaskState.WORKING
    }
}