package com.bangkit.naksu.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit.naksu.ui.theme.Primary
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bangkit.naksu.R
import com.bangkit.naksu.model.Menus
import com.bangkit.naksu.model.MenusData
import com.bangkit.naksu.navigation.Screen

@Composable
fun HomeScreen(navController: NavHostController) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .scrollable(scrollState, orientation = Orientation.Vertical)
    ) {
        Banner()
        MenuSection(navController = navController)
        Spacer(modifier = Modifier.height(16.dp))
        Insight(navController = navController)
    }
}

@Composable
fun Insight(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .shadow(elevation = 24.dp, shape = RoundedCornerShape(30.dp))
            .clip(RoundedCornerShape(30.dp))
            .background(color = Color.White)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        DetailItem(
            title = "What is Sundanese Script ? ",
            desc = "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim velit mollit. ",
            route = Screen.Detail1.route,
            navController = navController
        )
        DetailItem(
            title = "About Us",
            desc = "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim velit mollit. ",
            route = Screen.About.route,
            navController = navController
        )
    }
}

@Composable
fun DetailItem(title: String, desc: String, route: String, navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                // TODO 2 : Navigate to Detail Screen
                navController.navigate(route)
            }
        ,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .size(77.dp)
                .background(color = Color(0xFFD9D9D9)), contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_placeholder),
                contentDescription = "Image Placeholder",
                modifier = Modifier
                    .size(22.dp)
            )
        }
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = desc, fontSize = 12.sp)
        }
    }
}

@Composable
fun MenuSection(navController: NavHostController) {
    val data = MenusData.menus

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 50.dp),
        verticalArrangement = Arrangement.spacedBy(28.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MenuItem(menu = data[0], navController = navController)
            MenuItem(menu = data[1], navController = navController)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MenuItem(menu = data[2], navController = navController)
            MenuItem(menu = data[3], navController = navController)
        }
    }
}

@Composable
fun MenuItem(menu: Menus, navController: NavHostController) {
    val backgroundColor = Primary

    val color = Color.Black

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(55.dp)
                .width(55.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickable {
                    // TODO 1 : Navigate to Detail Screen
                    navController.navigate(menu.route)
                }
                .background(color = backgroundColor),
        ) {
            val tintColor = Color.White
            Icon(
                painter = painterResource(id = menu.image),
                contentDescription = menu.title,
                tint = tintColor,
                modifier = Modifier.size(28.dp)
            )
        }
        Text(
            text = menu.title,
            color = color,
            fontSize = 12.sp,
            fontFamily = FontFamily(Font(R.font.roboto))
        )
    }
}

@Composable
fun Banner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
            .background(Primary)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(id = R.string.home),
                color = Color.White,
                fontSize = 18.sp,
                fontFamily = FontFamily(
                    Font(R.font.roboto_medium, FontWeight.Medium)
                ),
            )

            Text(
                text = "Hi, there",
                color = Color.White,
                fontSize = 24.sp,
                fontFamily = FontFamily(
                    Font(R.font.roboto_medium, FontWeight.Medium)
                ),
                modifier = Modifier.padding(top = 36.dp)
            )
            Text(
                text = "Welcome to Naksu",
                color = Color.White,
                fontSize = 24.sp,
                fontFamily = FontFamily(
                    Font(R.font.roboto_medium, FontWeight.Medium)
                ),
            )

        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePreview(navController: NavHostController = rememberNavController()) {
    HomeScreen(navController = navController)
}