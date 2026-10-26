package com.example.myfirstapp.data.applist

import com.example.myfirstapp.domain.applist.AppListItem
import com.example.myfirstapp.domain.applist.AppListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AppListRepositoryImpl : AppListRepository {
    override fun getApps(): Flow<List<AppListItem>> = flow {
        // Имитация задержки загрузки (как будто из сети)
        kotlinx.coroutines.delay(500)

        // Хардкод DTO данных
        val dtoList = listOf(
            AppListItemDto("1", "ВКонтакте", "Социальная сеть", "Социальные", "https://placekitten.com/200/200"),
            AppListItemDto("2", "Telegram", "Мессенджер", "Общение", "https://placekitten.com/201/200"),
            AppListItemDto("3", "Яндекс Карты", "Навигация", "Карты", "https://placekitten.com/202/200"),
            AppListItemDto("4", "Сбербанк", "Банк", "Финансы", "https://placekitten.com/203/200"),
            AppListItemDto("5", "Кинопоиск", "Кино", "Развлечения", "https://placekitten.com/204/200")
        )

        // Конвертируем DTO в Domain model через Mapper
        val domainList = dtoList.map { it.toDomain() }
        emit(domainList)
    }
}