package com.afs.mobile.data.entity

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.afs.mobile.R

enum class TaskState(@ColorRes val colorRes: Int, @StringRes val nextAction: Int) {
    OPEN(android.R.color.white, R.string.task_start_travel),
    TRAVELLING(R.color.status_travelling, R.string.task_start_work),
    WORKING(R.color.status_working, R.string.task_stop);

    fun mapState() = when (this) {
        OPEN -> TRAVELLING
        TRAVELLING -> WORKING
        WORKING -> OPEN
    }

    fun isOpen() = this == OPEN
}