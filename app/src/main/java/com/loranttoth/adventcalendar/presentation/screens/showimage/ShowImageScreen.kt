package com.loranttoth.adventcalendar.presentation.screens.showimage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.ImageLoader
import coil.compose.AsyncImage
import com.loranttoth.adventcalendar.presentation.screens.main.MainScreenViewModel

@Composable
fun ShowImageScreen(
    navigateToMain: () -> Unit,
    snackbarHostState: SnackbarHostState,
    viewModel: MainScreenViewModel,
    imageLoader: ImageLoader
) {
        Column(
            modifier = Modifier.fillMaxSize()

        ) {
            viewModel.actImageItem?.value?.let {
                AsyncImage(
                    model = viewModel.actImageItem!!.value!!.sourceUrl,
                    imageLoader = imageLoader,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null,
                )
            }
        }
}