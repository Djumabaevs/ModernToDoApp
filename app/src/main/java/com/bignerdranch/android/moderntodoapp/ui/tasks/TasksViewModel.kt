package com.bignerdranch.android.moderntodoapp.ui.tasks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bignerdranch.android.moderntodoapp.data.TaskDao

class TasksViewModel @ViewModelInject constructor(
    private val taskDao: TaskDao
)
    : ViewModel() {

        val tasks = taskDao.getTasks().asLiveData()

}
