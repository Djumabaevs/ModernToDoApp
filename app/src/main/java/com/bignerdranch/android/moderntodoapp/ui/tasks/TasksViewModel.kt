package com.bignerdranch.android.moderntodoapp.ui.tasks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bignerdranch.android.moderntodoapp.data.TaskDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

class TasksViewModel @ViewModelInject constructor(
    private val taskDao: TaskDao
)
    : ViewModel() {

    val searchQuery = MutableStateFlow("")
    val sortOrder = MutableStateFlow(SortOrder.BY_DATE)
    val hideCompleted = MutableStateFlow(false)

    private val  taskFlow = searchQuery.flatMapLatest {
        taskDao.getTasks(it)
    }

        val tasks = taskFlow.asLiveData()

}

enum class SortOrder {BY_NAME, BY_DATE}
