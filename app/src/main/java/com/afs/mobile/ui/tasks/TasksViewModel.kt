package com.afs.mobile.ui.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afs.mobile.data.entity.Result
import com.afs.mobile.data.entity.Task
import com.afs.mobile.data.entity.doOnSuccess
import com.afs.mobile.ext.hasVarietyTaskStatuses
import com.afs.mobile.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TasksViewModel(private val repository: TaskRepository) : ViewModel() {

    init {
        getTasks()
    }

    private val _tasks: MutableLiveData<Result<List<Task>>> by lazy { MutableLiveData<Result<List<Task>>>() }
    val task: LiveData<Result<List<Task>>> = _tasks

    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            val taskWithNextState = task.copy(state = task.state.mapState())
            repository.updateTask(taskWithNextState)
        }
    }

    private fun getTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getTasks().doOnSuccess {
                it.collect { tasks ->
                    if (tasks.isEmpty()) initTaskFetch()
                    withContext(Dispatchers.Main) {
                        val newTasks = if (tasks.hasVarietyTaskStatuses()) changeTasksState(tasks) else tasks
                        _tasks.value = Result.Success(newTasks)
                    }
                }
            }
        }
    }

    private fun changeTasksState(tasks: List<Task>) = tasks.map { task ->
        if (task.state.isOpen()) task.copy(taskActivation = false) else task
    }

    private fun initTaskFetch() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchTasks()
        }
    }
}