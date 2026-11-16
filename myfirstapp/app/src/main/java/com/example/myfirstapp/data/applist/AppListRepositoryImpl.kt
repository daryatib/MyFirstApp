package com.example.myfirstapp.data.applist

import com.example.myfirstapp.domain.applist.AppListItem
import com.example.myfirstapp.domain.applist.AppListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AppListRepositoryImpl @Inject constructor() : AppListRepository {
    override fun getApps(): Flow<List<AppListItem>> = flow {
        // Эмуляция загрузки из сети (моковые данные)
        val mockList = listOf(
            AppListItem("1", "ВКонтакте", "Социальная сеть", "Соцсети", ""),
            AppListItem("2", "Telegram", "Мессенджер", "Общение", ""),
            AppListItem("3", "Яндекс Карты", "Навигация", "Карты", ""),
            AppListItem("4", "Spotify", "Музыка", "Музыка", ""),
            AppListItem("5", "Netflix", "Фильмы", "Видео", ""),
            AppListItem("6", "TikTok", "Видео", "Развлечения", ""),
            AppListItem("7", "Discord", "Общение", "Соцсети", ""),
            AppListItem("8", "Duolingo", "Языки", "Образование", ""),
            AppListItem("9", "Notion", "Заметки", "Работа", ""),
            AppListItem("10", "Pinterest", "Идеи", "Развлечения", "")
        )
        emit(mockList)
    }
}