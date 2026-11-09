package com.example.myfirstapp.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CatalogDto(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("description") val description: String? = null,
    @SerialName("iconUrl") val iconUrl: String? = null,
    @SerialName("category") val category: String? = null,
    @SerialName("developer") val developer: String? = null,
    @SerialName("ageRating") val ageRating: Int? = null,
    @SerialName("size") val size: Float? = null
)