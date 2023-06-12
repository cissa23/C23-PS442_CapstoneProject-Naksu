package com.bangkit.naksu.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bangkit.naksu.R
import com.bangkit.naksu.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnimLogo = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 2000,
        )
    )

    val alphaAnimCircle = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000,
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(2000)
        navController.navigate(Screen.Home.route)
    }

    Splash(alpha1 = alphaAnimLogo.value, alpha2 = alphaAnimCircle.value)
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun SplashScreenPreview() {
//    SplashScreen()
//}

@Composable
fun Splash(alpha1: Float, alpha2: Float) {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.alpha(alpha1)) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(190.dp)
            )
            Text(
                text = stringResource(id = R.string.app_name).uppercase(),
                color = Color.Black,
                fontSize = 24.sp,
                fontFamily = FontFamily(
                    Font(R.font.roboto_bold, FontWeight.Bold)
                ),
            )
            Text(
                text = stringResource(id = R.string.app_name_long),
                color = Color.Black,
                fontSize = 14.sp,
                fontFamily = FontFamily(
                    Font(R.font.roboto, FontWeight.Normal)
                ),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }

    Box(modifier = Modifier.offset(x = (-200).dp, y = (-300).dp).alpha(alpha2)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val center = Offset(size.width / 2f, size.height / 2f)
            val radius = size.width / 2f
            drawCircle(
                color = Color(0xFFD80027).copy(alpha = 0.5f),
                center = center,
                radius = radius
            )
        }
    }

    Box(modifier = Modifier.offset(x = 280.dp, y = 230.dp).alpha(alpha2)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val center = Offset(size.width / 2f, size.height / 2f)
            val radius = size.width / 2f
            drawCircle(
                color = Color(0xFFD80027).copy(alpha = 0.5f),
                center = center,
                radius = radius
            )
        }
    }
}