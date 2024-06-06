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

package com.rachel.remote.di

import android.content.Context
import com.rachel.remote.CalorieApi
import com.rachel.remote.interceptors.NetworkInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import timber.log.Timber

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun providesNetworkInterceptor(@ApplicationContext context: Context) = NetworkInterceptor(context)

    @Singleton
    @Provides
    fun providesHttpClient(networkInterceptor: NetworkInterceptor) =
        HttpClient(OkHttp) {
            engine { this.addInterceptor(networkInterceptor) }

            install(ContentNegotiation) { json(json = Json { encodeDefaults = false }) }

            install(Logging) {
                level = LogLevel.BODY
                object : Logger {
                    override fun log(message: String) {
                        Timber.i("LOGGER", "BODY -> \n$message")
                    }
                }
            }

            install(DefaultRequest) {
                header(HttpHeaders.Accept, ContentType.Application.Json)
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                header("x-api-key", "8/MqBej61B6ALLuEf7cIWg==tJbmaSTQHGZd6wLJ")
            }
        }

    @Singleton @Provides fun providesCalorieApi(client: HttpClient) = CalorieApi(client)
}
