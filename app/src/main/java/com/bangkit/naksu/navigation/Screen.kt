package com.bangkit.naksu.navigation

sealed class Screen(val route: String) {
    object Splash: Screen("splash_screen")
    object Home: Screen("home_screen")
    object Detail1: Screen("detail1_screen")
    object About: Screen("about_screen")
    object Study: Screen("study_screen")
    object Dictionary: Screen("dictionary_screen")
}