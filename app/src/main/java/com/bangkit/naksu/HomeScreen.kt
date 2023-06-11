package com.bangkit.naksu

import android.graphics.Color.green
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.SnackbarDefaults.backgroundColor
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bangkit.naksu.model.Menus
import com.bangkit.naksu.model.MenusData
import com.bangkit.naksu.ui.theme.Progress

@Composable
fun HomeScreen(navController: NavHostController) {
    var progress by remember {
        mutableStateOf(0.5f)
    }

    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize().scrollable(scrollState, orientation = Orientation.Vertical)) {
        Banner()
        LearningCard(progress)
        MenuSection()
    }
}

@Composable
fun MenuSection() {
    val data = MenusData.menus

    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp),verticalArrangement = Arrangement.spacedBy(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MenuItem(menu = data[0])
            MenuItem(menu = data[1])
            MenuItem(menu = data[2])
        }
        MenuItem(menu = data[3])
    }
}

@Composable
fun MenuItem(menu: Menus) {
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

                }
                .background(color = backgroundColor),
        ) {
            val tintColor =Color.White
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
fun LearningCard(progress: Float) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = (-20).dp)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp))
            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(
                text = "Learned Today",
                fontSize = 12.sp,
                color = Color(R.color.gray),
                fontFamily = FontFamily(
                    Font(R.font.roboto)
                ),
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "30min", fontSize = 20.sp, color = Color.Black, fontFamily = FontFamily(
                        Font(R.font.roboto_bold, FontWeight.Bold)
                    ), modifier = Modifier.alignByBaseline()
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "/ 60min",
                    fontSize = 10.sp,
                    color = Color(R.color.gray),
                    fontFamily = FontFamily(
                        Font(R.font.roboto)
                    ),
                    modifier = Modifier.alignByBaseline()
                )
            }

            // Progress Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp)
                    .padding(top = 5.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.3f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(R.color.bg_progress))
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color.White, Progress
                                ), startX = 0.5f, endX = Float.POSITIVE_INFINITY
                            )
                        )
                )
            }
        }
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
        Text(
            text = stringResource(id = R.string.home),
            color = Color.White,
            fontSize = 18.sp,
            fontFamily = FontFamily(
                Font(R.font.roboto_medium, FontWeight.Medium)
            ),
        )
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePreview(navController: NavHostController = rememberNavController()) {
    HomeScreen(navController = navController)
}