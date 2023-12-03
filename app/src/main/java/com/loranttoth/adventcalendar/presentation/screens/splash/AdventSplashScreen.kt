package com.loranttoth.adventcalendar.presentation.screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.loranttoth.adventcalendar.R
import com.loranttoth.adventcalendar.presentation.navigation.AdventScreens
import kotlinx.coroutines.delay

@Composable
fun AdventSplashScreen(navController: NavController) {
    val alpha = remember {
        Animatable(0.0f)
    }
    LaunchedEffect(key1 = true, block = {
         alpha.animateTo(targetValue = 1.0f,
            animationSpec = tween(
                durationMillis = 1000,
        ))
        delay(1500L)
        navController.navigate(AdventScreens.MainScreen.name)
    } )

        Column(modifier = Modifier.padding(1.dp).size(600.dp)
            .alpha(alpha.value),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            ) {
            Image(
                painter = painterResource(id = R.drawable.christmaslogo),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                alpha = alpha.value,
                modifier = Modifier.size(300.dp).scale(0.8f)
            )
        }
}