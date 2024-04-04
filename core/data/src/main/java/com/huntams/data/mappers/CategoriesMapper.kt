package com.example.data.mappers


import com.example.network.model.model.ApiCategories
import com.example.network.model.model.ApiMeals
import com.example.network.model.model.ApiProduct
import com.huntams.model.Categories
import com.huntams.model.Category
import com.huntams.model.Meals
import com.huntams.model.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesMapper @Inject constructor() {

    fun apiCategoriesToModel(apiMeals: ApiMeals<ApiProduct>) = Meals(
        meals = apiMeals.meals.map {
            Product(
                strMeal=it.strMeal,
            strMealThumb=it.strMealThumb,
            idMeal=it.idMeal,
                strInstructions = it.strInstructions,
                strCategory = it.strCategory)
        }
    )

    fun apiProductsCategoriesToModel(apiCategories: ApiCategories) =
        Categories(
            apiCategories.category.map {
                Category(
                    idCategory = it.idCategory,
                    strCategory = it.strCategory
                )
            }
        )

}