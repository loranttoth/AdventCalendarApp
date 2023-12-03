package com.loranttoth.adventcalendar.presentation.screens.main

import android.app.Activity
import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.ImageLoader
import com.loranttoth.adventcalendar.domain.entity.AdventImageEntity

@Composable
fun MainScreen(
    navigateToShowImage: () -> Unit,
    snackbarHostState: SnackbarHostState,
    viewModel: MainScreenViewModel,
    imageLoader: ImageLoader
) {
    val activity = (LocalContext.current as? Activity)
    BackHandler(true) {
        activity?.finish()
    }

    var colNum = when (LocalConfiguration.current.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> 4
        else -> 8
    }
    val calendarPagingItems = viewModel.adventPagingFlow.collectAsLazyPagingItems()

    if (calendarPagingItems.loadState.refresh is LoadState.Error) {
        LaunchedEffect(key1 = snackbarHostState) {
            snackbarHostState.showSnackbar(
                (calendarPagingItems.loadState.refresh as LoadState.Error).error.message ?: ""
            )
        }
    }

    Scaffold(
        topBar = { MainTopBar(viewModel)},
    )
    {padding ->
        AdventContent(
            calendarPagingItems = calendarPagingItems,
            navigateToShowImage = navigateToShowImage,
            colNum,
            viewModel,
            imageLoader
        )
    }
}

@Composable
fun AdventContent(
    calendarPagingItems: LazyPagingItems<AdventImageEntity>,
    navigateToShowImage: () -> Unit,
    colNum: Int,
    viewModel: MainScreenViewModel,
    imageLoader: ImageLoader
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(start = 0.dp, top = 30.dp, end = 0.dp, bottom = 0.dp)) {
        if (calendarPagingItems.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(colNum),
                modifier = Modifier.fillMaxSize(),
                //horizontalAlignment = Alignment.CenterHorizontally,

            ) {
                items(
                    count = calendarPagingItems.itemCount,
                    key = calendarPagingItems.itemKey { it.position },
                ) { index ->
                    val calendarItem = calendarPagingItems[index]
                    if (calendarItem != null) {
                        DayItem(
                            calendarItem,
                            onClick = {
                                clickOnWindow(calendarItem, viewModel, navigateToShowImage)
                            },
                            viewModel,
                            modifier = Modifier.fillMaxWidth()
                                .height(100.dp)
                                .padding(5.dp),
                            imageLoader
                        )
                    }
                }
            }
        }
    }
}

fun clickOnWindow(adventImageEntity: AdventImageEntity, viewModel: MainScreenViewModel, navigateToShowImage: () -> Unit) {
    viewModel.actImageItem.value = adventImageEntity
    navigateToShowImage()
}