package com.afs.mobile.data.database

import androidx.room.TypeConverter
import com.afs.mobile.data.entity.TaskState

class TaskStateConverter {

    @TypeConverter
    fun fromTaskState(value: TaskState): String {
        return value.name
    }

    @TypeConverter
    fun toTaskState(value: String): TaskState {
        return when(value) {
            "OPEN" -> TaskState.OPEN
            "TRAVELLING" -> TaskState.TRAVELLING
            else -> TaskState.WORKING
        }
    }
}