package com.bignerdranch.android.moderntodoapp.ui.tasks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bignerdranch.android.moderntodoapp.data.PreferencesManager
import com.bignerdranch.android.moderntodoapp.data.SortOrder
import com.bignerdranch.android.moderntodoapp.data.TaskDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest

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

}


