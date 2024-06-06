package com.rachel.local.dao


import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE


interface BaseDao<T> {

    @Insert(onConflict = REPLACE)
    suspend fun insert(item: T): Long

    @Insert(onConflict = REPLACE)
    suspend fun insert(vararg items: T)

    @Insert(onConflict = REPLACE)
    suspend fun insert(items: List<T>)

}