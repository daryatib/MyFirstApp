package com.example.myfirstapp.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myfirstapp.domain.appdetails.AppDetails
import com.example.myfirstapp.domain.appdetails.Category
import com.example.myfirstapp.domain.appdetails.SimilarApp
import com.example.myfirstapp.presentation.appdetails.AppDetailsScreen
import com.example.myfirstapp.presentation.applist.AppListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // Список из 9 приложений для Витрины (пока оставим здесь)
    val mockSimilarApps = List(9) { index ->
        SimilarApp(
            id = "similar-$index",
            name = "Приложение ${index + 1}",
            iconUrl = "https://placekitten.com/${210 + index}/200"
        )
    }

    // Функция для получения карточки по ID (захардкожено)
    fun getAppDetails(id: String): AppDetails {
        // В реальном приложении здесь был бы запрос в репозиторий
        return AppDetails(
            id = id,
            name = "Приложение $id",
            developer = "Разработчик",
            category = Category.APP,
            ageRating = 12,
            size = 150.5f,
            iconUrl = "https://placekitten.com/300/300",
            screenshotUrlList = listOf(
                "https://placekitten.com/800/450",
                "https://placekitten.com/801/450"
            ),
            description = "Описание для приложения с ID $id."
        )
    }

    NavHost(navController = navController, startDestination = "app_list") {
        composable("app_list") {
            AppListScreen(
                onAppClick = { app ->
                    navController.navigate("app_details/${app.id}")
                }
            )
        }
        composable("app_details/{appId}") { backStackEntry ->
            val appId = backStackEntry.arguments?.getString("appId") ?: "1"
            val appDetails = getAppDetails(appId)

            AppDetailsScreen(
                appDetails = appDetails,
                similarApps = mockSimilarApps,
                onBackClick = { navController.popBackStack() },
                onShareClick = {},
                onInstallClick = {},
                onReadMoreClick = {},
                onDeveloperClick = {},
                onSimilarAppDownloadClick = {
                    navController.popBackStack()
                },
            )
        }
    }
}