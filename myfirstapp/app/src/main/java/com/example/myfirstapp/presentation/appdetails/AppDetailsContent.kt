package com.example.myfirstapp.presentation.appdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myfirstapp.domain.appdetails.AppDetails
import com.example.myfirstapp.domain.appdetails.Category
import com.example.myfirstapp.domain.appdetails.SimilarApp
import com.example.myfirstapp.ui.theme.MyFirstAppTheme

@Composable
fun AppDetailsContent(
    content: AppDetailsState.Content,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    onInstallClick: () -> Unit,
    onReadMoreClick: () -> Unit,
    onDeveloperClick: () -> Unit,
    onSimilarAppDownloadClick: (SimilarApp) -> Unit,
    modifier: Modifier = Modifier,
) {
    val appDetails = content.appDetails
    val descriptionCollapsed = content.descriptionCollapsed

    Column(modifier.verticalScroll(rememberScrollState())) {
        Toolbar(
            onBackClick = onBackClick,
            onShareClick = onShareClick,
        )
        Spacer(Modifier.height(8.dp))
        AppDetailsHeader(
            appDetails = appDetails,
            modifier = Modifier.padding(horizontal = 16.dp),
        )
        Spacer(Modifier.height(16.dp))
        InstallButton(
            onClick = onInstallClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        if (content.similarApps.isNotEmpty()) {
            Spacer(Modifier.height(12.dp))
            SimilarApps(
                apps = content.similarApps,
                onDownloadClick = onSimilarAppDownloadClick,
                modifier = Modifier.padding(horizontal = 16.dp),
            )
        }
        Spacer(Modifier.height(12.dp))
        ScreenshotsList(
            screenshotUrlList = listOf("1", "2", "3"),
            contentPadding = PaddingValues(horizontal = 16.dp),
        )
        Spacer(Modifier.height(12.dp))
        AppDescription(
            description = appDetails.description,
            collapsed = descriptionCollapsed,
            onReadMoreClick = onReadMoreClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        )
        Spacer(Modifier.height(12.dp))
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            color = MaterialTheme.colorScheme.outlineVariant,
        )
        Spacer(Modifier.height(12.dp))
        Developer(
            name = appDetails.developer,
            onClick = onDeveloperClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
        )

        // --- БЛОК "ВИТРИНА" ---
        Spacer(Modifier.height(24.dp))
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            color = MaterialTheme.colorScheme.outlineVariant,
        )
        Spacer(Modifier.height(16.dp))
        ShowcaseBlock(
            apps = content.similarApps,
            onAppClick = { app -> onSimilarAppDownloadClick(app) },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(24.dp))
    }
}

@Preview
@Composable
private fun Preview() {
    MyFirstAppTheme {
        AppDetailsContent(
            content = AppDetailsState.Content(
                appDetails = AppDetails(
                    id = "fa2e31b8-1234-4cf7-9914-108a170a1b01",
                    name = "Гильдия Героев: Экшен ММО РПГ",
                    developer = "VK Play",
                    category = Category.GAME,
                    ageRating = 12,
                    size = 223.7f,
                    screenshotUrlList = listOf(
                        "https://picsum.photos/seed/scr1/800/450",
                        "https://picsum.photos/seed/scr2/800/450",
                    ),
                    iconUrl = "https://picsum.photos/seed/icon/200",
                    description = "Легендарный рейд героев в Фэнтези РПГ. Станьте героем гильдии и сразите мастера подземелья!"
                ),
                similarApps = List(9) { index ->
                    SimilarApp(
                        id = "similar-app-$index",
                        name = "Приложение $index",
                        iconUrl = "https://picsum.photos/seed/sim$index/200",
                    )
                },
                descriptionCollapsed = false,
            ),
            onReadMoreClick = {},
            onBackClick = {},
            onShareClick = {},
            onInstallClick = {},
            onDeveloperClick = {},
            onSimilarAppDownloadClick = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}