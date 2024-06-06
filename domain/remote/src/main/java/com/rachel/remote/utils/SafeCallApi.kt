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

package com.rachel.remote.utils

import com.rachel.remote.models.NetworkResult
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

internal suspend inline fun <reified T> safeApiCall(block: () -> HttpResponse): NetworkResult<T> {
    return try {
        val response = block()
        return when (response.status) {
            HttpStatusCode.OK ->
                NetworkResult(isSuccessful = true, message = "Success", data = response.body() as T)
            HttpStatusCode.Unauthorized -> NetworkResult(message = "Unauthorized")
            else -> NetworkResult(message = "Failed Request")
        }
    } catch (e: Exception) {
        val message = e.message ?: "Error"
        NetworkResult(message = message)
    }
}
