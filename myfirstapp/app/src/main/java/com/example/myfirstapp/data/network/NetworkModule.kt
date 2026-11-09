package com.example.myfirstapp.data.network

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object NetworkModule {
    private const val BASE_URL = "http://185.103.109.134/"

    private val json = Json {
        ignoreUnknownKeys = true // Важно: игнорирует лишние поля в JSON
        isLenient = true
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    val apiService: CatalogApiService = retrofit.create(CatalogApiService::class.java)
}