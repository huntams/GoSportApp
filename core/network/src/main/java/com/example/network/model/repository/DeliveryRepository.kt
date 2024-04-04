package com.example.network.repository


import com.huntams.model.Categories
import com.huntams.model.Meals
import com.huntams.model.Product

interface DeliveryRepository {

    suspend fun getProducts(): Meals<Product>
    suspend fun getCategories(): Categories
}