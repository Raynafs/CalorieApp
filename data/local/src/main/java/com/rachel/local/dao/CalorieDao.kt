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

package com.rachel.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.rachel.local.models.NutrientsEntity
import kotlinx.coroutines.flow.Flow

/**
 * The interface provides methods for querying and modifying the calories table in the Room database,
 * using Kotlin coroutines and Flow to provide asynchronous and reactive data access capabilities.
 * */
@Dao
interface CalorieDao : BaseDao<NutrientsEntity> {

    @Query("SELECT * FROM calories") fun searchNutrients(): Flow<List<NutrientsEntity>>

    @Query("SELECT * FROM calories WHERE name LIKE '%' || :query || '%'")
    fun searchCalories(vararg query: String): List<NutrientsEntity>

    @Query("SELECT * FROM calories WHERE name = :name")
    fun getCalorie(name: String): Flow<NutrientsEntity>

    @Query("DELETE FROM calories") fun deleteAll()
}
