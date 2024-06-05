package com.rachel.data.models
sealed class Resource {
    data class Error(val message: String) : Resource()
    data class Success(val isEmpty: Boolean) : Resource()
}