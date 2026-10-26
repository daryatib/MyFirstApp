package com.example.myfirstapp.presentation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myfirstapp.data.applist.AppListRepositoryImpl
import com.example.myfirstapp.domain.appdetails.AppDetails
import com.example.myfirstapp.domain.appdetails.Category
import com.example.myfirstapp.domain.appdetails.SimilarApp
import com.example.myfirstapp.domain.applist.GetAppListUseCase
import com.example.myfirstapp.presentation.appdetails.AppDetailsScreen
import com.example.myfirstapp.presentation.applist.AppListScreen
import com.example.myfirstapp.presentation.applist.AppListViewModel

// Фабрика для создания ViewModel с зависимостями
class AppListViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppListViewModel::class.java)) {
            val repository = AppListRepositoryImpl()
            val useCase = GetAppListUseCase(repository)
            @Suppress("UNCHECKED_CAST")
            return AppListViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val mockSimilarApps = List(9) { index ->
        SimilarApp(
            id = "similar-$index",
            name = "Приложение ${index + 1}",
            iconUrl = "https://placekitten.com/${210 + index}/200"
        )
    }

    fun getAppDetails(id: String): AppDetails {
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
            // Создаем ViewModel через фабрику
            val viewModel: AppListViewModel = viewModel(factory = AppListViewModelFactory())

            AppListScreen(
                onAppClick = { app ->
                    navController.navigate("app_details/${app.id}")
                },
                viewModel = viewModel
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