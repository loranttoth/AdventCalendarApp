package com.loranttoth.adventcalendar.presentation.screens.main

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ShowLocker(
    isOpenable: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(
            modifier = Modifier
                .weight(1f)
        )
        if (!isOpenable) {
            Icon(
                imageVector = Icons.Default.Lock,
                tint = Color.Gray,
                contentDescription = null,
                modifier = Modifier
                    .padding(0.dp, 5.dp, 0.dp, 0.dp)
            )
        }
    }
}