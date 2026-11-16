package com.example.myfirstapp.presentation.appdetails

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myfirstapp.domain.appdetails.SimilarApp

@Composable
fun AppDetailsScreen(
    appId: String,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    onReadMoreClick: () -> Unit,
    onDeveloperClick: () -> Unit,
    onSimilarAppDownloadClick: (SimilarApp) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AppDetailsViewModel = hiltViewModel()
) {
    // Загружаем данные при старте
    LaunchedEffect(appId) {
        viewModel.loadAppDetails(appId)
    }

    val state by viewModel.state.collectAsState()

    Scaffold(modifier = modifier) { contentPadding ->
        when (val currentState = state) {
            is AppDetailsState.Loading -> AppDetailsLoading()
            is AppDetailsState.Error -> AppDetailsError(onRefreshClick = { viewModel.loadAppDetails(appId) })
            is AppDetailsState.Content -> {
                AppDetailsContent(
                    content = currentState,
                    onBackClick = onBackClick,
                    onShareClick = onShareClick,
                    onInstallClick = viewModel::onInstallClick, // Кнопка теперь работает!
                    onReadMoreClick = onReadMoreClick,
                    onDeveloperClick = onDeveloperClick,
                    onSimilarAppDownloadClick = onSimilarAppDownloadClick,
                    modifier = Modifier
                        .fillMaxSize()
                        .safeDrawingPadding()
                        .padding(contentPadding),
                )
            }
        }
    }
}