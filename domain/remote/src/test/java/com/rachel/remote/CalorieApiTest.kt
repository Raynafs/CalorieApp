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
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Test
import kotlin.test.DefaultAsserter.assertEquals
import kotlin.test.assertTrue

class CalorieApiTest {
    companion object {
        val ACCURATE_RESPONSE = """
            {
            "items": [
            {
            "name": "apple",
            "calories": 52.0,
            "serving_size_g": 100.0,
            "fat_total_g": 0.2,
            "fat_saturated_g": 0.0,
            "protein_g": 0.3,
            "sodium_mg": 1,
            "potassium_mg": 107,
            "cholesterol_mg": 0,
            "carbohydrates_total_g": 14.0,
            "fiber_g": 2.4,
            "sugar_g": 10.0
            }
            	]
        }
    """.trimIndent()

        val INACCURATE_RESPONSE = """
            {
            	"items": [
            		{
            			"name": "apple",
            			"calories": 52.0,
            			"serving_size_g": 100.0,
            			"fat_saturated_g": 0.0,
            			"protein_g": 0.3,
            			"sodium_mg": 1,
            			"potassium_mg": 107,
            			"cholesterol_mg": 0,
            			"carbohydrates_total_g": 14.0,
            			"sugar_g": 10.0
            		}
            	]
            }
        """.trimIndent()

        private fun generateDummyClient(engine: HttpClientEngine) = HttpClient(engine) {
            install(ContentNegotiation) {
                json()
            }
            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
        }

    }

    @Test
    fun `given an accurate response CalorieApi returns successful NetworkResult`() = runTest {

        val fakeEngine = MockEngine {
            respond(
                content = ACCURATE_RESPONSE,
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val fakeClient = generateDummyClient(engine = fakeEngine)

        val actualResult = CalorieApi(client = fakeClient).getCalories(query = "apple")

        assertTrue { actualResult.isSuccessful }

    }

    @Test
    fun `given a accurate response CalorieApi returns valid ItemsDto data`() = runTest {

        val fakeEngine = MockEngine {
            respond(
                content = ACCURATE_RESPONSE,
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val fakeClient = generateDummyClient(engine = fakeEngine)

        val expected = Json.decodeFromString<ItemsDto>(ACCURATE_RESPONSE)
        val actual = CalorieApi(client = fakeClient).getCalories(query = "apple")

        val data = actual.data

        assertEquals(message = "parses valid calorie items", expected = expected, actual = data)
    }

    @Test
    fun `given a inaccurate response CalorieApi returns unsuccessful NetworkResult`() = runTest {

        val fakeEngine = MockEngine {
            respond(
                content = INACCURATE_RESPONSE,
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val fakeClient = generateDummyClient(engine = fakeEngine)

        val actualResult = CalorieApi(client = fakeClient).getCalories(query = "apple")

        assertTrue{ actualResult.isSuccessful.not() }

    }

    @Test
    fun `given a inaccurate response CalorieApi returns null as NetworkResult data`() = runTest {

        val fakeEngine = MockEngine {
            respond(
                content = INACCURATE_RESPONSE,
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val fakeClient = generateDummyClient(engine = fakeEngine)

        val actualResult = CalorieApi(client = fakeClient).getCalories(query = "apple")

        val data = actualResult.data

        assertEquals(
            message = "returns null when parsing response data",
            expected = null,
            actual = data
        )
    }

}
