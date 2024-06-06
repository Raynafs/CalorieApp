package com.rachel.remote

import com.rachel.remote.models.ItemsDto
import com.rachel.remote.models.NetworkResult
import com.rachel.remote.utils.safeApiCall
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*


class CalorieApi(
    private val client: HttpClient
) {

    private val baseUrl = "https://api.calorieninjas.com/v1/nutrition"

    suspend fun getCalories(query: String): NetworkResult<ItemsDto> = safeApiCall {
        val response = client.get(baseUrl) {
            url { parameters.append("query", query) }
        }
        response.body()
    }

}