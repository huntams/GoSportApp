package com.example.data.repository

import com.example.data.mappers.CategoriesMapper
import com.example.network.model.DeliveryApiService
import com.example.network.repository.DeliveryRepository
import com.huntams.model.Categories
import com.huntams.model.Meals
import com.huntams.model.Product
import javax.inject.Inject

class DeliveryRepositoryImpl @Inject constructor(
    private val deliveryApiService: DeliveryApiService,
    private val categoriesMapper: CategoriesMapper,
) : DeliveryRepository {


    override suspend fun getProducts(): Meals<Product> {
        return categoriesMapper.apiCategoriesToModel(
            deliveryApiService.getProducts(
            )
        )
    }

    override suspend fun getCategories(): Categories {
        return categoriesMapper.apiProductsCategoriesToModel(deliveryApiService.getCategories())
    }
}