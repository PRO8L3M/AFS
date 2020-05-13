package com.afs.mobile.ext

import com.afs.mobile.common.OPEN_STATUS
import com.afs.mobile.data.entity.Task

fun Iterable<Task>.hasVarietyTaskStatuses(): Boolean = any { task -> task.state.name != OPEN_STATUS }