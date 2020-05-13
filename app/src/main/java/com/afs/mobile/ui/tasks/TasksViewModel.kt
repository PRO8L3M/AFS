package com.afs.mobile.ui.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.afs.mobile.data.entity.Task
import com.afs.mobile.data.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TasksViewModel(private val repository: TaskRepository) : ViewModel() {

    val task: LiveData<List<Task>> = repository.getTasks().asLiveData()

    fun insertTasks(tasks: List<Task>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertTasks(tasks)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTask(task)
        }
    }

    fun getMockedTasks() = repository.getMockedTasks()
}