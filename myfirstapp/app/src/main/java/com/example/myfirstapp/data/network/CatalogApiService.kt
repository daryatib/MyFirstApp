package com.example.myfirstapp.data.network

import kotlinx.coroutines.delay
import retrofit2.http.GET
import retrofit2.http.Path

interface CatalogApiService {
    @GET("catalog")
    suspend fun getCatalog(): List<CatalogDto>

    @GET("{id}")
    suspend fun getAppDetails(@Path("id") id: String): AppDetailsDto

    // Эмуляция рейтинга
    suspend fun getRating(id: String): Float {
        delay(1500)
        return (30..50).random() / 10f
    }
}