package com.afs.mobile.ui.tasks

import androidx.lifecycle.*
import com.afs.mobile.data.entity.Task
import com.afs.mobile.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TasksViewModel(private val repository: Repository) : ViewModel() {

    val task: LiveData<List<Task>> = repository.getTasks().asLiveData()

    fun insertTask(task: Task) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.insertTask(task)
            }
        }
    }

    fun insertTasks(tasks: List<Task>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.insertTasks(tasks)
            }
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.updateTask(task)
            }
        }
    }
}