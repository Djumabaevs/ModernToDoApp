package com.bignerdranch.android.moderntodoapp.ui.tasks

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.moderntodoapp.data.Task
import com.bignerdranch.android.moderntodoapp.databinding.ItemTaskBinding

class TasksAdapter : ListAdapter<Task,> {

    class TasksViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {

        }
    }
}