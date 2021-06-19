package com.bignerdranch.android.moderntodoapp.data

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat

@Entity(tableName = "task_table")
@Parcelize
data class Task(
    val name: String,
    val important: Boolean = false,
    val completed: Boolean = false,
    val created: Long = System.currentTimeMillis()
) : Parcelable {
    val createdDateFormatted: String
    get() = DateFormat.getDateTimeInstance().format(created)
}
