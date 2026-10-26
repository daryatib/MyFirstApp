package com.example.myfirstapp.domain.applist

import kotlinx.coroutines.flow.Flow

class GetAppListUseCase(
    private val repository: AppListRepository
) {
    operator fun invoke(): Flow<List<AppListItem>> {
        return repository.getApps()
    }
}