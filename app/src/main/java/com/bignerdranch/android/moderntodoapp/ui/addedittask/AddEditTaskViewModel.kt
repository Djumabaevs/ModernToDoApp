package com.bignerdranch.android.moderntodoapp.ui.addedittask

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.moderntodoapp.data.Task
import com.bignerdranch.android.moderntodoapp.data.TaskDao

class AddEditTaskViewModel @ViewModelInject constructor(
    private val taskDao: TaskDao,
   @Assisted private val state: SavedStateHandle
) : ViewModel() {

    val task = state.get<Task>("task")

    var taskName = state.get<String>("taskName") ?: task?.name ?: ""


}