package com.example.myfirstapp.di

import com.example.myfirstapp.data.applist.AppListRepositoryImpl
import com.example.myfirstapp.domain.applist.AppListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindAppListRepository(
        impl: AppListRepositoryImpl
    ): AppListRepository
}