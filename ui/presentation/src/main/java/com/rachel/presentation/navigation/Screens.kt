package com.rachel.presentation.navigation
sealed class Screens(val route: String) {
    object Calories : Screens(route = "calories")
    object Nutrients : Screens(route = "nutrients")
}