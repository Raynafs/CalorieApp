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

package com.rachel.remote

import com.rachel.remote.models.ItemsDto
import com.rachel.remote.models.NetworkResult
import com.rachel.remote.utils.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

/** This class is responsible for making network calls.
 * It sends a request to the api with a given food query and retrieves the calorie and other nutrients details.
 * The getCalories method, sends a GET request to the API's nutrition endpoint
 * and returns the response encapsulated in a NetworkResult object.
 * The function uses a safeApiCall method to handle the API call safely.
 * */

class CalorieApi(private val client: HttpClient) {

    private val baseUrl = "https://api.calorieninjas.com/v1/nutrition"

    suspend fun getCalories(query: String): NetworkResult<ItemsDto> = safeApiCall {
        val response = client.get(baseUrl) { url { parameters.append("query", query) } }
        response.body()
    }
}
