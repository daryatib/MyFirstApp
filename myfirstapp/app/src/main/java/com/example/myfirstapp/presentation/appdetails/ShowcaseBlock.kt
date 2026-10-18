package com.example.myfirstapp.presentation.appdetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myfirstapp.domain.appdetails.SimilarApp

@Composable
fun ShowcaseBlock(
    apps: List<SimilarApp>,
    onAppClick: (SimilarApp) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Text(
            text = "Витрина",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(horizontal = 16.dp),
        )
        Spacer(Modifier.height(8.dp))

        // Используем LazyRow с горизонтальным скроллом
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(apps) { app ->
                SimilarAppItem(
                    app = app,
                    onDownloadClick = { onAppClick(app) },
                    modifier = Modifier.width(128.dp), // Фиксированная ширина для элемента
                )
            }
        }
    }
}