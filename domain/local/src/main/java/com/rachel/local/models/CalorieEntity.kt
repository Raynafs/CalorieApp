package com.rachel.local.modelsmodels

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "calories")
data class CalorieEntity(
    @PrimaryKey
    val name: String = UUID.randomUUID().toString(),
    val calories: Double,
    val servingSizeGrams: Double,
    val fatTotalGrams: Double,
    val fatSaturatedGrams: Double,
    val proteinGrams: Double,
    val sodiumMilligrams: Double,
    val potassiumMilligrams: Double,
    val cholesterolMilligrams: Double,
    val carbohydratesTotalGrams: Double,
    val fiberGrams: Double,
    val sugarGrams: Double
)