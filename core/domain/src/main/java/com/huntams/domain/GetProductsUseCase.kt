package com.huntams.domain

import com.example.network.repository.DeliveryRepository
import com.huntams.model.Meals
import com.huntams.model.Product
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val deliveryRepository: DeliveryRepository
) {
    suspend operator fun invoke(): Meals<Product> {
        return deliveryRepository.getProducts()
    }
}