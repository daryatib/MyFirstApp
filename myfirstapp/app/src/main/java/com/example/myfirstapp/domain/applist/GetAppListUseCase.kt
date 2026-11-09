package com.example.myfirstapp.domain.applist

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAppListUseCase @Inject constructor(
    private val repository: AppListRepository
) {
    operator fun invoke(): Flow<List<AppListItem>> {
        return repository.getApps()
    }
}