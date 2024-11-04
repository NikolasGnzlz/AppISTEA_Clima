package com.example.appclima_nikognzlz

sealed class Screen(val route: String) {
    object Cities : Screen("cities")
    object Weather : Screen("weather")
}