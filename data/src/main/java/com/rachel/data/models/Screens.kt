package com.rachel.data.models
sealed class Screens(val route: String) {
    object Calories : Screens(route = "calories")
    object Calorie : Screens(route = "calorie")
}