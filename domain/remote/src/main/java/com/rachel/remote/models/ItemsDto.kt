package com.rachel.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemsDto(
    @SerialName("items") var items: List<CalorieDto>
)