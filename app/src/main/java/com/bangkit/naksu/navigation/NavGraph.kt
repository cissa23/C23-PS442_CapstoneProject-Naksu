package com.bangkit.naksu.navigation

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bangkit.naksu.screen.*
import java.io.File
import java.util.concurrent.ExecutorService

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    outputDirectory: File,
    executor: ExecutorService,
    showCamera: MutableState<Boolean>
) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.Detail1.route) {
            HomeDetailScreen(
                title = "What is Sundanese Script ?",
                navController = navController,
                content = "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim velit mollit."
            )
        }
        composable(route = Screen.About.route) {
            HomeDetailScreen(
                title = "About Us",
                navController = navController,
                content = "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim velit mollit."
            )
        }
        composable(route = Screen.Study.route) {
            StudyScreen(navController = navController, showCamera = showCamera)
        }
        composable(route = Screen.Camera.route) {

            fun handleImageCapture(uri: Uri) {
                Log.i("naksu", "Image captured: $uri")
                showCamera.value = false
            }

            if (showCamera.value) {
                CameraScreen(
                    outputDirectory = outputDirectory,
                    executor = executor,
                    onImageCaptured = {
                        handleImageCapture(it)
                    },
                    onError = {
                        Log.e("kilo", "Error capturing image: $it")
                    })
            } else {
                StudyScreen(navController = navController,showCamera = showCamera)
            }
        }
    }
}