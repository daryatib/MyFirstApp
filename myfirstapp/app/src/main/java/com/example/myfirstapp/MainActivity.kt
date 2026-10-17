package com.example.myfirstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.myfirstapp.domain.applist.AppListItem
import com.example.myfirstapp.presentation.applist.AppListScreen
import com.example.myfirstapp.ui.theme.MyFirstAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFirstAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val mockApps = listOf(
                        AppListItem("1", "ВКонтакте", "Социальная сеть", "Социальные", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/VK.com_Logo_2021.svg/1200px-VK.com_Logo_2021.svg.png"),
                        AppListItem("2", "Telegram", "Мессенджер", "Общение", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/82/Telegram_logo.svg/1200px-Telegram_logo.svg.png"),
                        AppListItem("3", "Яндекс Карты", "Навигация", "Карты", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Yandex_Maps_icon.svg/1200px-Yandex_Maps_icon.svg.png"),
                        AppListItem("4", "Сбербанк", "Банк", "Финансы", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e9/Sberbank_Logo_2020.svg/1200px-Sberbank_Logo_2020.svg.png"),
                        AppListItem("5", "Кинопоиск", "Кино", "Развлечения", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cd/Kinopoisk_logo.svg/1200px-Kinopoisk_logo.svg.png")
                    )
                    AppListScreen(apps = mockApps)
                }
            }
        }
    }
}