package com.bangkit.naksu.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bangkit.naksu.R
import com.bangkit.naksu.ui.theme.Background

@Composable
fun BottomNav(selected: String, route: String, navController: NavHostController) {
    val gradientColors = listOf(Color(0xFFF2873A), Color(0xFFD80027))
    val brush = Brush.linearGradient(gradientColors)
    BottomNavigation(
        backgroundColor = Background,
        contentColor = Color.Black,
        elevation = 1.dp,
        modifier = Modifier.height(80.dp)
    ) {
        BottomNavigationItem(
            selected = selected === "image",
            onClick = { /* TODO: Handle item click */ },
            icon = {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.ic_galeri),
                        contentDescription = "Image",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Image", fontSize = 12.sp)
                }
            },
        )
        BottomNavigationItem(
            selected = selected === "home",
            onClick = { navController.navigate("home_screen") },
            icon = {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.ic_home),
                        contentDescription = "Home",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Home", fontSize = 12.sp)
                }
            },
        )
        BottomNavigationItem(
            selected = selected === "camera",
            onClick = { /* TODO: Handle item click */ },
            icon = {
                Box(
                    modifier = Modifier
                        .fillMaxSize(0.7f)
                        .clip(shape = CircleShape)
                        .background(brush),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_kamera),
                        contentDescription = "Camera",
                        modifier = Modifier.size(24.dp),
                        tint = Color.White
                    )
                }
            }
        )
        BottomNavigationItem(
            selected = selected === "dictionary",
            onClick = { /* TODO: Handle item click */ },
            icon = {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.ic_book),
                        contentDescription = "Dictionary",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Dictionary", fontSize = 12.sp)
                }
            },
        )
        BottomNavigationItem(
            selected = selected === "collection",
            onClick = { /* TODO: Handle item click */ },
            icon = {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = "Collecction",
                        modifier = Modifier.size(26.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Collection", fontSize = 12.sp)
                }
            },
        )
    }
}