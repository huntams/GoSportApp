package com.huntams.model

import java.text.NumberFormat
import kotlin.random.Random

data class Product(
    val idMeal : Int,
    val strMeal: String,
    val strCategory : String,
    val strInstructions: String,
    val strMealThumb: String,
    val price: Int = Random.nextInt(300, 1000)
){
    fun getFormattedPrice(): String = NumberFormat.getCurrencyInstance().format(price)
}