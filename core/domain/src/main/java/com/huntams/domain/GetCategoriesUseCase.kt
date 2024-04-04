package com.huntams.domain

import com.example.network.repository.DeliveryRepository
import com.huntams.model.Categories
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val deliveryRepository: DeliveryRepository
) {
    suspend operator fun invoke(): Categories {
        return deliveryRepository.getCategories()
    }
}