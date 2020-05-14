package com.afs.mobile.ui.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import com.afs.mobile.R
import com.afs.mobile.common.BaseFragment
import com.afs.mobile.common.DOUBLE_BACK_PRESSED_DURATION
import com.afs.mobile.common.ERROR_OCCURRED
import com.afs.mobile.data.entity.Task
import com.afs.mobile.ext.snackBar
import com.afs.mobile.ext.toast
import com.afs.mobile.util.ResultObserver
import kotlinx.android.synthetic.main.fragment_start.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TasksFragment : BaseFragment() {

    private val viewModel: TasksViewModel by viewModel()
    private val tasksAdapter = TasksAdapter()
    private var isDoubleClicked = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeTaskLiveData()
        setupRecyclerView()
        handleRecyclerClickEvents()
        onDoubleBackPressed()
    }

    private fun observeTaskLiveData() {
        viewModel.task.observe(viewLifecycleOwner, ResultObserver(::onSuccess, ::onFailure, ::onLoading))
    }

    private fun handleRecyclerClickEvents() {
        updateTask()
    }

    private fun updateTask() {
        tasksAdapter.onButtonClick =  { task -> viewModel.updateTask(task) }
    }

    private fun setupRecyclerView() {
        tasksRecyclerView.adapter = tasksAdapter
    }

    private fun onSuccess(tasks: List<Task>) {
        swapList(tasks)
    }
    private fun onFailure(exception: Exception) {
        snackBar(exception.localizedMessage ?: ERROR_OCCURRED)
    }

    private fun onLoading() {}

    private fun swapList(tasks: List<Task>) = tasksAdapter.swapList(tasks)

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

    override fun onDestroyView() {
        super.onDestroyView()

        onRecyclerViewDetached(tasksRecyclerView)
    }
}