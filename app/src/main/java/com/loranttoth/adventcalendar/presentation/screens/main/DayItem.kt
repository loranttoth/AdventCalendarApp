package com.loranttoth.adventcalendar.presentation.screens.main

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.loranttoth.adventcalendar.domain.entity.AdventImageEntity

@Composable
fun DayItem(
    calendarItem: AdventImageEntity,
    onClick: () -> Unit,
    mainScreenViewModel: MainScreenViewModel,
    modifier: Modifier = Modifier,
    imageLoader: ImageLoader
) {
    val isOpenedList = mainScreenViewModel
        .openedList.collectAsState().value.filter { item ->
            (calendarItem.position == item.day)
        }

    var isOpened = !isOpenedList.isNullOrEmpty()

    var isOpenable = mainScreenViewModel.isOpenable(calendarItem.position)

    var alpha = if (isOpened) {1f} else {0.3f}

    ElevatedCard(
        modifier = modifier
            .clickable {
                if (isOpenable) {
                    if (!isOpened) {
                        mainScreenViewModel.openWindow(calendarItem)
                    }
                    mainScreenViewModel.actImageItem.value = calendarItem
                    onClick()
                }
            }
            .padding(horizontal = 0.dp, vertical = 0.dp)
            .fillMaxHeight(),
        colors = CardDefaults.cardColors( containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),

        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    model = calendarItem.thumbnail,
                    imageLoader = imageLoader,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    alpha = alpha
                )
                if (!isOpened) {
                    ShowWindow()
                }
                ShowLocker(isOpenable)
                ShowDayNumber(calendarItem)
            }
        }
    )
}