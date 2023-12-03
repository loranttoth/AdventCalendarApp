package com.loranttoth.adventcalendar.presentation.screens.main

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ShowWindow() {
    Row(
        modifier = Modifier
            .fillMaxSize()
    )
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
        ){
            Canvas(modifier = Modifier.fillMaxSize()) {
                val canvasWidth = size.width
                val canvasHeight = size.height
                drawLine(
                    start = Offset(x = canvasWidth / 2, y = 0f),
                    end = Offset(x = canvasWidth / 2, y = canvasHeight),
                    strokeWidth = 3.dp.toPx(),
                    color = Color.Black
                )
                drawLine(
                    start = Offset(x = 0.dp.toPx(), y = canvasHeight / 2),
                    end = Offset(x = canvasWidth, y = canvasHeight / 2),
                    strokeWidth = 3.dp.toPx(),
                    color = Color.Black
                )
            }
        }
    }
}