package com.bangkit.naksu.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bangkit.naksu.components.AppBar
import com.bangkit.naksu.R
import com.bangkit.naksu.components.BottomNav
import com.bangkit.naksu.navigation.Screen
import com.bangkit.naksu.ui.theme.Background
import com.bangkit.naksu.ui.theme.BackgroundBorder

@Composable
fun StudyScreen(navController: NavHostController, showCamera: MutableState<Boolean>) {
    Scaffold(
        topBar = {
            AppBar(title = "Study Room")
        },
        bottomBar = {
            BottomNav(selected = "study", route = Screen.Study.route, navController = navController, showCamera = showCamera)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(20.dp)
        ) {
            var lang1 by remember { mutableStateOf("Sundanese") }
            var lang2 by remember { mutableStateOf("Latin") }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 5.dp, shape = RoundedCornerShape(5.dp))
                    .clip(RoundedCornerShape(5.dp))
                    .border(
                        width = 1.dp,
                        color = BackgroundBorder,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .background(Background)
                    .padding(vertical = 16.dp, horizontal = 28.dp)
                    .align(Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = lang1,
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_swap),
                    contentDescription = "Swap",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { lang1 = lang2.also { lang2 = lang1 } })
                Text(
                    text = lang2,
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            
            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(216.dp)
                    .shadow(elevation = 5.dp, shape = RoundedCornerShape(5.dp))
                    .clip(RoundedCornerShape(5.dp))
                    .border(
                        width = 1.dp,
                        color = BackgroundBorder,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .background(Background)
                    .padding(vertical = 16.dp, horizontal = 28.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                Text(
                    text = lang1,
                    color = Color(0xFF003366),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Close",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { })

                }
            }

            Spacer(modifier = Modifier.height(26.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(216.dp)
                    .shadow(elevation = 5.dp, shape = RoundedCornerShape(5.dp))
                    .clip(RoundedCornerShape(5.dp))
                    .border(
                        width = 1.dp,
                        color = BackgroundBorder,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .background(Background)
                    .padding(vertical = 16.dp, horizontal = 28.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = lang2,
                        color = Color(0xFF003366),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )

                }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(painter = painterResource(id = R.drawable.ic_copy), contentDescription = "Copy",tint = Color(0xFF003366), modifier = Modifier.size(24.dp).clickable {  })
                        Icon(painter = painterResource(id = R.drawable.ic_share), contentDescription = "Copy", tint = Color(0xFF003366),modifier = Modifier.size(24.dp).clickable {  })
                        Icon(painter = painterResource(id = R.drawable.ic_star), contentDescription = "Copy", tint = Color(0xFF003366),modifier = Modifier.size(24.dp).clickable {  })
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewStudyScreen(navController: NavHostController = rememberNavController()) {
    StudyScreen(navController = navController, showCamera = remember { mutableStateOf(false) })
}