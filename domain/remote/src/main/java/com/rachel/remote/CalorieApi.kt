package com.rachel.remote

import com.rachel.remote.models.ItemsDto
import com.rachel.remote.models.NetworkResult
import com.rachel.remote.utils.safeApiCall
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import timber.log.Timber
import javax.inject.Inject


class CalorieApi(
    val client: HttpClient
) {

    private val baseUrl = "https://api.calorieninjas.com/v1/nutrition"

    suspend fun getCalories(query: String): NetworkResult<ItemsDto> = safeApiCall {
        val response = client.get(baseUrl) {
            url { parameters.append("query", query) }
        }
        response.body()
    }

}