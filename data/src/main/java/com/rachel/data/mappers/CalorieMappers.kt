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

package com.rachel.data.mappers

import com.rachel.data.models.Calorie
import com.rachel.local.models.NutrientsEntity
import com.rachel.remote.models.CalorieDto

fun CalorieDto?.toUiModel() =
    this?.let {
        Calorie(
            name,
            calories,
            servingSizeGrams,
            fatTotalGrams,
            fatSaturatedGrams,
            proteinGrams,
            sodiumMilligrams,
            potassiumMilligrams,
            cholesterolMilligrams,
            carbohydratesTotalGrams,
            fiberGrams,
            sugarGrams
        )
    }

fun NutrientsEntity?.toUiModel() =
    this?.let {
        Calorie(
            name,
            calories,
            servingSizeGrams,
            fatTotalGrams,
            fatSaturatedGrams,
            proteinGrams,
            sodiumMilligrams,
            potassiumMilligrams,
            cholesterolMilligrams,
            carbohydratesTotalGrams,
            fiberGrams,
            sugarGrams
        )
    }

fun Calorie?.toDatabaseModel() =
    this?.let {
        NutrientsEntity(
            name,
            calories,
            servingSizeGrams,
            fatTotalGrams,
            fatSaturatedGrams,
            proteinGrams,
            sodiumMilligrams,
            potassiumMilligrams,
            cholesterolMilligrams,
            carbohydratesTotalGrams,
            fiberGrams,
            sugarGrams
        )
    }
