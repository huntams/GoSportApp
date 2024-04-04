package com.example.network.model.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiCategory(
    val idCategory: String,
    val strCategory: String,
)
