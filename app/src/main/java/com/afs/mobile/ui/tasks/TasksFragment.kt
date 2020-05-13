package com.afs.mobile.ui.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.Observer
import com.afs.mobile.R
import com.afs.mobile.common.BaseFragment
import com.afs.mobile.common.DOUBLE_BACK_PRESSED_DURATION
import com.afs.mobile.data.entity.Task
import com.afs.mobile.data.entity.TaskState
import com.afs.mobile.ext.toast
import kotlinx.android.synthetic.main.fragment_start.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class TasksFragment : BaseFragment() {

    private val viewModel: TasksViewModel by viewModel()
    private val tasksAdapter = TasksAdapter()
    private var isDoubleClicked = false

    private val tasks = listOf(
        Task(name = "London", state = TaskState.OPEN),
        Task(name = "Chicago", state = TaskState.OPEN),
        Task(name = "Berlin", state = TaskState.OPEN),
        Task(name = "Rio", state = TaskState.OPEN),
        Task(name = "Los Angeles", state = TaskState.OPEN),
        Task(name = "Dubai", state = TaskState.OPEN),
        Task(name = "Wroclaw", state = TaskState.OPEN),
        Task(name = "Paris", state = TaskState.OPEN)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.task.observe(viewLifecycleOwner, Observer {
            insertNewList(it)
        })

       // viewModel.insertTasks(tasks)
        setUpRecyclerView()
        handleRecyclerClickEvents()
        onDoubleBackPressed()
    }

    private fun handleRecyclerClickEvents() {
        tasksAdapter.onButtonClick = { task ->
            val newTask = when(task.state) {
                TaskState.OPEN -> task.copy(state = TaskState.TRAVELLING)
                TaskState.TRAVELLING -> task.copy(state = TaskState.WORKING)
                TaskState.WORKING -> task.copy(state = TaskState.OPEN)
            }
            viewModel.updateTask(newTask)
        }
    }

    private fun setUpRecyclerView() {
        tasksRecyclerView.adapter = tasksAdapter
    }

    private fun insertNewList(tasks: List<Task>) = tasksAdapter.swapNewList(tasks)

    private fun onDoubleBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if (isDoubleClicked) {
                requireActivity().finish()
            }
            isDoubleClicked = true
            toast(resources.getString(R.string.sign_in_exit_app))
            viewScope.launch {
                delay(DOUBLE_BACK_PRESSED_DURATION)
                isDoubleClicked = false
            }
        }
    }
}