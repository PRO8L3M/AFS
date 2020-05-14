package com.afs.mobile.data.remoteDataSource

import com.afs.mobile.data.entity.Task
import com.afs.mobile.data.entity.TaskState

class RemoteDataSource {

    fun fetchTask() = getMockedTasks()

    private fun getMockedTasks() = listOf(
        Task(name = "London", state = TaskState.OPEN),
        Task(name = "Chicago", state = TaskState.OPEN),
        Task(name = "Berlin", state = TaskState.OPEN),
        Task(name = "Rio", state = TaskState.OPEN),
        Task(name = "Los Angeles", state = TaskState.OPEN),
        Task(name = "Dubai", state = TaskState.OPEN),
        Task(name = "Wroclaw", state = TaskState.OPEN),
        Task(name = "Warszawa", state = TaskState.OPEN),
        Task(name = "Gdynia", state = TaskState.OPEN),
        Task(name = "Dortmund", state = TaskState.OPEN),
        Task(name = "Hamburg", state = TaskState.OPEN),
        Task(name = "Oslo", state = TaskState.OPEN),
        Task(name = "Otta", state = TaskState.OPEN),
        Task(name = "Lillehammer", state = TaskState.OPEN),
        Task(name = "Trondheim", state = TaskState.OPEN),
        Task(name = "Reykjavik", state = TaskState.OPEN),
        Task(name = "Vik i Myrdal", state = TaskState.OPEN),
        Task(name = "Antalya", state = TaskState.OPEN),
        Task(name = "Goteborg", state = TaskState.OPEN),
        Task(name = "Malmo", state = TaskState.OPEN),
        Task(name = "Rzym", state = TaskState.OPEN)
    )
}
