package com.loranttoth.adventcalendar.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.loranttoth.adventcalendar.domain.entity.AdventImageEntity

@Composable
fun ShowDayNumber(
    calendarItem: AdventImageEntity
) {
    Row(
        modifier = Modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Bottom
    ) {
        Box(modifier = Modifier
            .size(40.dp)
            .padding(5.dp)
            .background(Color.LightGray, CircleShape),
            contentAlignment = Alignment.Center
        )
        {
            Text(
                text = "${calendarItem.position}",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                modifier = Modifier
                    .padding(5.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}