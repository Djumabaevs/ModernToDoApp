package com.bignerdranch.android.moderntodoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bignerdranch.android.moderntodoapp.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    class Callback @Inject constructor(
        private val database: Provider<TaskDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao =  database.get().taskDao()

            applicationScope.launch {
                dao.insert(Task("Buy the car", important = true))
                dao.insert(Task("Rent a house"))
                dao.insert(Task("Buy a house"))
                dao.insert(Task("Rent a cottage"))
                dao.insert(Task("Do the shopping"))
                dao.insert(Task("Sell the flat at top price", completed = true))
            }

        }
    }

}