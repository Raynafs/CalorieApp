package com.rachel.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.rachel.local.modelsmodels.CalorieEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface CalorieDao : BaseDao<CalorieEntity> {

    @Query("SELECT * FROM calories")
    fun searchCalories(): Flow<List<CalorieEntity>>

    @Query("SELECT * FROM calories WHERE name LIKE '%' || :query || '%'")
    fun searchCalories(vararg query: String): List<CalorieEntity>

    @Query("SELECT * FROM calories WHERE name = :name")
    fun getCalorie(name: String): Flow<CalorieEntity>

    @Query("DELETE FROM calories")
    fun deleteAll()

}