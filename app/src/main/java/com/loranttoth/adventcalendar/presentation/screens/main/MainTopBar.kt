package com.loranttoth.adventcalendar.presentation.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.loranttoth.adventcalendar.R

@Composable
fun MainTopBar(
    viewModel: MainScreenViewModel
) {
    Row(
        modifier = Modifier.height(30.dp)
            .padding(horizontal = 0.dp, vertical = 5.dp)
    )
    {
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = R.drawable.christmaslogo),
            contentDescription = "logo",

            )
        Text(
            "Advent Calendar",
            modifier = Modifier
                .weight(1f)
                .padding(start = 20.dp, top = 0.dp, end = 0.dp, bottom = 0.dp),
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(modifier = Modifier)
        Text(text = viewModel.getDateString(),
            modifier = Modifier
                .padding(start = 0.dp, top = 0.dp, end = 5.dp, bottom = 0.dp),
            style = MaterialTheme.typography.labelLarge
        )
    }
}