package com.example.myfirstapp.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myfirstapp.domain.appdetails.AppDetails
import com.example.myfirstapp.domain.appdetails.Category
import com.example.myfirstapp.domain.appdetails.SimilarApp
import com.example.myfirstapp.domain.applist.AppListItem
import com.example.myfirstapp.presentation.appdetails.AppDetailsScreen
import com.example.myfirstapp.presentation.applist.AppListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // Список приложений для главного экрана
    val mockApps = listOf(
        AppListItem("1", "ВКонтакте", "Социальная сеть", "Социальные", "https://placekitten.com/200/200"),
        AppListItem("2", "Telegram", "Мессенджер", "Общение", "https://placekitten.com/201/200"),
        AppListItem("3", "Яндекс Карты", "Навигация", "Карты", "https://placekitten.com/202/200"),
        AppListItem("4", "Сбербанк", "Банк", "Финансы", "https://placekitten.com/203/200"),
        AppListItem("5", "Кинопоиск", "Кино", "Развлечения", "https://placekitten.com/204/200")
    )

    // Список из 9 приложений для Витрины
    val mockSimilarApps = List(9) { index ->
        SimilarApp(
            id = "similar-$index",
            name = "Приложение ${index + 1}", // <-- ИСПРАВЛЕНО: теперь 1, 2, 3... 9
            iconUrl = "https://placekitten.com/${210 + index}/200"
        )
    }

    // Функция для получения карточки по ID (захардкожено)
    fun getAppDetails(id: String): AppDetails {
        val app = mockApps.find { it.id == id } ?: mockApps.first()
        return AppDetails(
            id = app.id,
            name = app.name,
            developer = "Разработчик ${app.name}",
            category = Category.APP,
            ageRating = 12,
            size = 150.5f,
            iconUrl = app.iconUrl, // Берем иконку из списка
            screenshotUrlList = listOf(
                "https://placekitten.com/800/450", // <-- ИСПРАВЛЕНО
                "https://placekitten.com/801/450"  // <-- ИСПРАВЛЕНО
            ),
            description = "Это описание для приложения ${app.name}. Здесь мог бы быть красивый текст."
        )
    }

    NavHost(navController = navController, startDestination = "app_list") {
        composable("app_list") {
            AppListScreen(
                apps = mockApps,
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
                    navController.popBackStack() // Возврат на список при клике из Витрины
                },
            )
        }
    }
}