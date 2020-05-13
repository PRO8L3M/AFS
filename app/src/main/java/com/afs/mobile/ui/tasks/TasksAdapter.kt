package com.afs.mobile.ui.tasks

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.afs.mobile.R
import com.afs.mobile.common.OPEN_STATUS
import com.afs.mobile.data.entity.Task
import com.afs.mobile.databinding.TaskViewBinding
import com.afs.mobile.ext.getAsyncListDiffer
import com.afs.mobile.ext.layoutInflater

class TasksAdapter : RecyclerView.Adapter<TaskViewHolder>() {

    var onButtonClick: ((Task) -> Unit)? = null
    var areButtonsDisabled = false

    private val differ = getAsyncListDiffer<Task> { oldItem, newItem -> oldItem.id == newItem.id }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding: TaskViewBinding = DataBindingUtil.inflate(parent.context.layoutInflater, R.layout.task_view, parent, false)
        return TaskViewHolder(binding)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(differ.currentList[position], onButtonClick, areButtonsDisabled)
    }

    fun swapNewList(list: List<Task>) {
        refreshAdapterWhenTaskStarted(list)
    } 
    
    private fun refreshAdapterWhenTaskStarted(tasks: List<Task>) {
        areButtonsDisabled = tasks.any { task -> task.state.name != OPEN_STATUS}
        differ.submitList(tasks) { notifyDataSetChanged() }
    }
}

class TaskViewHolder(private val binding: TaskViewBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(task: Task, onButtonClick: ((Task) -> Unit)?, isGrayOut: Boolean) = with(binding) {
        taskViewButton.visibility = if (task.state.name == OPEN_STATUS && isGrayOut) View.INVISIBLE else View.VISIBLE
        taskViewId.text = binding.root.resources.getString(R.string.task_id, task.id.toString())
        taskViewName.text = task.name
        taskViewLayout.setBackgroundColor(ContextCompat.getColor(binding.root.context, task.state.colorRes))
        taskViewStatus.text = binding.root.resources.getString(R.string.task_status, task.state.toString())
        taskViewButton.text = binding.root.resources.getString(task.state.nextAction)
        taskViewButton.setOnClickListener { onButtonClick?.invoke(task) }
    }
}