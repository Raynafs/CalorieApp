package com.rachel.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.rachel.local.models.NutrientsEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface CalorieDao : BaseDao<NutrientsEntity> {

    @Query("SELECT * FROM calories")
    fun searchNutrients(): Flow<List<NutrientsEntity>>

    @Query("SELECT * FROM calories WHERE name LIKE '%' || :query || '%'")
    fun searchCalories(vararg query: String): List<NutrientsEntity>

    @Query("SELECT * FROM calories WHERE name = :name")
    fun getCalorie(name: String): Flow<NutrientsEntity>

    @Query("DELETE FROM calories")
    fun deleteAll()

}