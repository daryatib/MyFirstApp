package com.example.myfirstapp.data.network

import retrofit2.http.GET
import retrofit2.http.Path

interface CatalogApiService {
    @GET("catalog")
    suspend fun getCatalog(): List<CatalogDto>

    @GET("{id}")
    suspend fun getAppDetails(@Path("id") id: String): AppDetailsDto
}