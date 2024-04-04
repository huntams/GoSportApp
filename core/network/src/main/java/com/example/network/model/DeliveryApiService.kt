package com.example.network.model

import com.example.network.model.model.ApiCategories
import com.example.network.model.model.ApiMeals
import com.example.network.model.model.ApiProduct
import retrofit2.http.GET

interface DeliveryApiService {

    @GET("search.php?s")
    suspend fun getProducts(
    ): ApiMeals<ApiProduct>


    @GET("categories.php")
    suspend fun getCategories(): ApiCategories
}