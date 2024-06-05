package com.rachel.remote.di

import android.content.Context
import android.util.Log
import com.rachel.remote.CalorieApi
import com.rachel.remote.interceptors.NetworkInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import timber.log.Timber
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun providesNetworkInterceptor(@ApplicationContext context: Context) =
        NetworkInterceptor(context)

    @Singleton
    @Provides
    fun providesHttpClient(networkInterceptor: NetworkInterceptor) = HttpClient(OkHttp) {

        engine {
            this.addInterceptor(networkInterceptor)
        }

        install(ContentNegotiation) {
            json(json = Json {
                encodeDefaults = false
            })
        }

        install(Logging) {
            level = LogLevel.BODY
            object: Logger {
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

    @Singleton
    @Provides
    fun providesCalorieApi(client: HttpClient) = CalorieApi(client)

}