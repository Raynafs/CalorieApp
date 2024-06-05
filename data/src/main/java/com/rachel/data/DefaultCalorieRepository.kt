package com.rachel.data

import com.rachel.data.models.Resource
import com.rachel.local.dao.CalorieDao
import com.rachel.remote.CalorieApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject


class DefaultCalorieRepository (
    val dao: CalorieDao,
    val api: CalorieApi
) : CalorieRepository {

    private val dispatcher = Dispatchers.IO

    override fun getCalories(): Flow<List<Calorie>> =
        dao.searchCalories().map { list -> list.mapNotNull { it.toUiModel() } }

    override fun getCalorie(name: String): Flow<Calorie> =
        dao.getCalorie(name = name).mapNotNull { it.toUiModel() }

    override suspend fun searchCalories(query: String): Resource {
        return withContext(dispatcher) {
            val response = api.getCalories(query = query)
            if (response.isSuccessful.not()) return@withContext Resource.Error(message = response.message)
            val calories = response.data?.items
            if (calories.isNullOrEmpty()) return@withContext Resource.Success(isEmpty = true)
            dao.deleteAll()
            dao.insert(calories.mapNotNull { it.toUiModel().toDatabaseModel() })
            Resource.Success(isEmpty = false)
        }
    }

}