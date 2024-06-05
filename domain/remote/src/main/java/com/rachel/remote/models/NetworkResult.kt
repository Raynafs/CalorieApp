package com.rachel.remote.models

data class NetworkResult<T>(
    val isSuccessful: Boolean = false,
    val data: T? = null,
    val message: String,
)