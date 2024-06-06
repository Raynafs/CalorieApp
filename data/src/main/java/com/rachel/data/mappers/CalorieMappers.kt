package com.rachel.data.mappers

import com.rachel.data.models.Calorie
import com.rachel.local.models.NutrientsEntity
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

fun NutrientsEntity?.toUiModel() = this?.let {
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