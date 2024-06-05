package com.rachel.data.mappers

import com.rachel.local.modelsmodels.CalorieEntity
import com.rachel.remote.models.CalorieDto


fun CalorieDto?.toUiModel() = this?.let {
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

fun CalorieEntity?.toUiModel() = this?.let {
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

fun Calorie?.toDatabaseModel() = this?.let {
    CalorieEntity(
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