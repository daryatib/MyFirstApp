package com.example.myfirstapp.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myfirstapp.presentation.appdetails.AppDetailsScreen
import com.example.myfirstapp.presentation.applist.AppListScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myfirstapp.presentation.applist.AppListViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "app_list") {
        composable("app_list") {
            val viewModel: AppListViewModel = hiltViewModel()

            AppListScreen(
                onAppClick = { app ->
                    // Передаем ID приложения на экран деталей
                    navController.navigate("app_details/${app.id}")
                },
                viewModel = viewModel
            )
        }

        composable("app_details/{appId}") { backStackEntry ->
            val appId = backStackEntry.arguments?.getString("appId") ?: ""

            // Вызываем AppDetailsScreen напрямую, ViewModel внутри него сам все загрузит
            AppDetailsScreen(
                appId = appId,
                onBackClick = { navController.popBackStack() },
                onShareClick = {},
                onReadMoreClick = {},
                onDeveloperClick = {},
                onSimilarAppDownloadClick = {
                    navController.popBackStack()
                },
            )
        }
    }
}