package com.bangkit.naksu.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bangkit.naksu.screen.HomeDetailScreen
import com.bangkit.naksu.screen.HomeScreen
import com.bangkit.naksu.screen.SplashScreen
import com.bangkit.naksu.screen.StudyScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.Detail1.route) {
            HomeDetailScreen(title = "What is Sundanese Script ?",navController = navController, content = "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim velit mollit.")
        }
        composable(route = Screen.About.route) {
            HomeDetailScreen(title = "About Us",navController = navController, content = "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim velit mollit.")
        }
        composable(route = Screen.Study.route) {
            StudyScreen(navController = navController)
        }
    }
}