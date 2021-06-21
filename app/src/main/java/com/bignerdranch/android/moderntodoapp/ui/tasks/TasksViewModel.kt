package com.bignerdranch.android.moderntodoapp.ui.tasks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.moderntodoapp.data.PreferencesManager
import com.bignerdranch.android.moderntodoapp.data.SortOrder
import com.bignerdranch.android.moderntodoapp.data.Task
import com.bignerdranch.android.moderntodoapp.data.TaskDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class TasksViewModel @ViewModelInject constructor(
    private val taskDao: TaskDao,
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    val searchQuery = MutableStateFlow("")

    val preferencesFlow = preferencesManager.preferencesFlow

/*
    val sortOrder = MutableStateFlow(SortOrder.BY_DATE)
    val hideCompleted = MutableStateFlow(false)
*/

    private val  taskFlow = combine (
        searchQuery,
        preferencesFlow
        /*sortOrder,
        hideCompleted*/
            ) {
        query, filterPreferences /*sortOrder, hideCompleted*/ ->
        /*Triple*/Pair(query,  filterPreferences)
    }.flatMapLatest { (query,  filterPreferences) ->
        taskDao.getTasks(query, filterPreferences.sortOrder, filterPreferences.hideCompleted)
    }

    val tasks = taskFlow.asLiveData()

    fun onSortOrderSelected(sortOrder: SortOrder) = viewModelScope.launch {
        preferencesManager.updateSortOrder(sortOrder)
    }

    fun onHideCompletedClick(hideCompleted: Boolean) = viewModelScope.launch {
        preferencesManager.updateHideCompleted(hideCompleted)
    }

    fun onTaskSelected(task: Task) {}

    fun onTaskCheckedChanged(task: Task, isChecked: Boolean) = viewModelScope.launch {
        taskDao.update(task.copy(completed = isChecked))
    }

    fun onTaskSwiped(task: Task) = viewModelScope.launch {
        taskDao.delete(task)
    }

}


