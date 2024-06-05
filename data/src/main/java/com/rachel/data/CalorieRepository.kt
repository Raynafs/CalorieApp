package com.rachel.data

import com.rachel.data.models.Calorie
import com.rachel.data.models.Resource
import kotlinx.coroutines.flow.Flow

interface CalorieRepository {

    fun getCalories(): Flow<List<Calorie>>

    fun getCalorie(name: String) : Flow<Calorie>

    suspend fun searchCalories(query: String) : Resource

}