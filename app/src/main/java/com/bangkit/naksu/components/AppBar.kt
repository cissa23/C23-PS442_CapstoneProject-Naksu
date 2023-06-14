package com.bangkit.naksu.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.bangkit.naksu.ui.theme.Primary

@Composable
fun AppBar(title: String) {
    TopAppBar(
        title = { Text(text = title, color = Color.White) },
        backgroundColor = Primary
    )
}