package com.afs.mobile.util

import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.databinding.BindingAdapter
import com.afs.mobile.common.OPEN_STATUS
import com.afs.mobile.data.entity.Task

@BindingAdapter("bind:buttonVisibility")
fun View.setButtonVisibility(task: Task) {
    visibility = if (task.state.name == OPEN_STATUS && task.taskActivation == 0) INVISIBLE else VISIBLE
}