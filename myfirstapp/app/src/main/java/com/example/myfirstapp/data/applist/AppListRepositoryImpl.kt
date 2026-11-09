package com.example.myfirstapp.data.applist

import com.example.myfirstapp.data.network.CatalogDto
import com.example.myfirstapp.data.network.NetworkModule
import com.example.myfirstapp.domain.applist.AppListItem
import com.example.myfirstapp.domain.applist.AppListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AppListRepositoryImpl @Inject constructor() : AppListRepository {
    override fun getApps(): Flow<List<AppListItem>> = flow {
        // Получаем данные с сервера
        val catalogDtoList = NetworkModule.apiService.getCatalog()

        // Преобразуем DTO в Domain-модели
        val domainList = catalogDtoList.map { dto ->
            AppListItem(
                id = dto.id,
                name = dto.name,
                description = dto.description ?: "Нет описания",
                category = dto.category ?: "Разное",
                iconUrl = dto.iconUrl ?: ""
            )
        }
        emit(domainList)
    }
}