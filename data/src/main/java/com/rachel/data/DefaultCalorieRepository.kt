/*
 * Copyright 2021-2024 The Calorie App Systems, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rachel.data

import com.rachel.data.mappers.toDatabaseModel
import com.rachel.data.mappers.toUiModel
import com.rachel.data.models.Calorie
import com.rachel.data.models.Resource
import com.rachel.local.dao.CalorieDao
import com.rachel.remote.CalorieApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.withContext

class DefaultCalorieRepository(val dao: CalorieDao, val api: CalorieApi) : CalorieRepository {

    private val dispatcher = Dispatchers.IO

    override fun getCalories(): Flow<List<Calorie>> =
        dao.searchNutrients().map { list -> list.mapNotNull { it.toUiModel() } }

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
