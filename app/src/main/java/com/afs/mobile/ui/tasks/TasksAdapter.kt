package com.afs.mobile.ui.tasks

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.afs.mobile.BR
import com.afs.mobile.R
import com.afs.mobile.common.OPEN_STATUS
import com.afs.mobile.data.entity.Task
import com.afs.mobile.databinding.TaskViewBinding
import com.afs.mobile.ext.getAsyncListDiffer
import com.afs.mobile.ext.hasManyTaskStatus
import com.afs.mobile.ext.layoutInflater

class TasksAdapter : RecyclerView.Adapter<TaskViewHolder>() {

    var onButtonClick: ((Task) -> Unit)? = null

    private val differ = getAsyncListDiffer<Task> { oldItem, newItem -> oldItem.id == newItem.id }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding: TaskViewBinding = DataBindingUtil.inflate(parent.context.layoutInflater, R.layout.task_view, parent, false)
        return TaskViewHolder(binding)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(differ.currentList[position], onButtonClick)
    }

    fun swapNewList(tasks: List<Task>) {
        //todo change name
        val newTasks = if (tasks.hasManyTaskStatus()) changeTasksActivationStates(tasks) else tasks
        differ.submitList(newTasks)
    }

    private fun changeTasksActivationStates(tasks: List<Task>): List<Task> = tasks.map { task ->
            if (task.state.name == OPEN_STATUS) task.copy(taskActivation = 0) else task.copy()
        }
}

class TaskViewHolder(private val binding: TaskViewBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(task: Task, onButtonClick: ((Task) -> Unit)?) = with(binding) {
        setVariable(BR.task, task)
        executePendingBindings()

        taskViewButton.setOnClickListener { onButtonClick?.invoke(task) }
    }
}