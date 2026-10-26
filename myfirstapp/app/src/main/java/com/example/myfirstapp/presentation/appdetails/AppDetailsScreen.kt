package com.example.myfirstapp.presentation.appdetails

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myfirstapp.domain.appdetails.AppDetails
import com.example.myfirstapp.domain.appdetails.Category
import com.example.myfirstapp.domain.appdetails.SimilarApp

@Composable
fun AppDetailsScreen(
    appDetails: AppDetails,
    similarApps: List<SimilarApp>,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    onInstallClick: () -> Unit,
    onReadMoreClick: () -> Unit,
    onDeveloperClick: () -> Unit,
    onSimilarAppDownloadClick: (SimilarApp) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(modifier = modifier) { contentPadding ->
        AppDetailsContent(
            content = AppDetailsState.Content(
                appDetails = appDetails,
                similarApps = similarApps,
                descriptionCollapsed = false,
            ),
            onBackClick = onBackClick,
            onShareClick = onShareClick,
            onInstallClick = onInstallClick,
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