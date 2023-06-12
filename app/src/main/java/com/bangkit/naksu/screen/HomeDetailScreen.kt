package com.bangkit.naksu.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bangkit.naksu.R
import com.bangkit.naksu.ui.theme.Primary

@Composable
fun HomeDetailScreen(title: String, img: Int, content: String, navController: NavHostController) {
    Column(horizontalAlignment = Alignment.Start, modifier = Modifier
        .fillMaxWidth()) {
        TopAppBar(title = { Text(text = "Home", color = Color.White) }, navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
        }, backgroundColor = Primary)
        Column(modifier = Modifier.padding(14.dp)) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .height(160.dp)
            .background(color = Color(0xFFD9D9D9))
            , contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_placeholder),
                contentDescription = "Image Placeholder",
                modifier = Modifier
                    .size(50.dp)
            )
        }

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview(navController: NavHostController = rememberNavController()) {
    HomeDetailScreen(title = "title", img = 0, content = "content", navController = navController)
}