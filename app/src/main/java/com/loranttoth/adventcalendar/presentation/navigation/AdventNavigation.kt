package com.loranttoth.adventcalendar.presentation.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.imageLoader
import com.loranttoth.adventcalendar.presentation.screens.main.MainScreen
import com.loranttoth.adventcalendar.presentation.screens.main.MainScreenViewModel
import com.loranttoth.adventcalendar.presentation.screens.showimage.ShowImageScreen
import com.loranttoth.adventcalendar.presentation.screens.splash.AdventSplashScreen
import okhttp3.OkHttpClient

@Composable
fun AdventNavigation() {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState()
    }

    val viewModel = hiltViewModel<MainScreenViewModel>()

    val imageLoader = LocalContext.current.imageLoader.newBuilder()
        .okHttpClient {
            OkHttpClient.Builder()
                .build()
        }
        .build()

    NavHost(navController = navController,
        startDestination = AdventScreens.SplashScreen.name ) {
        composable(AdventScreens.SplashScreen.name){
            AdventSplashScreen(navController = navController)
        }

        composable(AdventScreens.MainScreen.name){
            MainScreen(
                navigateToShowImage = { ->
                    navController.navigate(AdventScreens.ShowImageScreen.name)
                },
                snackbarHostState = snackbarHostState,
                viewModel,
                imageLoader)
        }

        val route = AdventScreens.ShowImageScreen.name
        composable("$route")
        {
            ShowImageScreen(
                navigateToMain = { ->
                    navController.navigate(AdventScreens.MainScreen.name){
                        popUpTo(AdventScreens.MainScreen.name) {
                            inclusive = true
                        }
                    }
                },
                snackbarHostState = snackbarHostState,
                viewModel,
                imageLoader
                )
        }

    }
}