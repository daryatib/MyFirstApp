package com.example.myfirstapp.domain.applist

import kotlinx.coroutines.flow.Flow

interface AppListRepository {
    fun getApps(): Flow<List<AppListItem>>
}