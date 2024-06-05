package com.rachel.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rachel.local.dao.CalorieDao
import com.rachel.local.modelsmodels.CalorieEntity


@Database(
    entities = [CalorieEntity::class],
    exportSchema = false,
    version = 7
)
abstract class CalorieDatabase : RoomDatabase() {

    abstract fun calorieDao(): CalorieDao

    companion object {
        const val DATABASE_NAME = "calories_database"
    }

}