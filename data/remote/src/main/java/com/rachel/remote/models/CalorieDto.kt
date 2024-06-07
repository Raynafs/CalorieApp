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

package com.rachel.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CalorieDto(
    @SerialName("name") val name: String,
    @SerialName("calories") val calories: Double,
    @SerialName("serving_size_g") val servingSizeGrams: Double,
    @SerialName("fat_total_g") val fatTotalGrams: Double,
    @SerialName("fat_saturated_g") val fatSaturatedGrams: Double,
    @SerialName("protein_g") val proteinGrams: Double,
    @SerialName("sodium_mg") val sodiumMilligrams: Double,
    @SerialName("potassium_mg") val potassiumMilligrams: Double,
    @SerialName("cholesterol_mg") val cholesterolMilligrams: Double,
    @SerialName("carbohydrates_total_g") val carbohydratesTotalGrams: Double,
    @SerialName("fiber_g") val fiberGrams: Double,
    @SerialName("sugar_g") val sugarGrams: Double
)
