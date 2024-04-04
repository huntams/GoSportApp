package com.example.network.model.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiCategories(
    @SerialName("categories")
    val category : List<ApiCategory>,
)