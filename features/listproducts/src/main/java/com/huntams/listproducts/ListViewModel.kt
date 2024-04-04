package com.huntams.listproducts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.huntams.data.base.BaseViewModel
import com.huntams.data.base.ResultLoader
import com.huntams.domain.GetCategoriesUseCase
import com.huntams.domain.GetProductsUseCase
import com.huntams.model.Categories
import com.huntams.model.Meals
import com.huntams.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getProductsUseCase: GetProductsUseCase
) : BaseViewModel() {
    private val _categoriesLiveData = MutableLiveData<ResultLoader<Categories>>()
    val categoriesLiveData: LiveData<ResultLoader<Categories>> = _categoriesLiveData

    private val _mealsLiveData =
        MutableLiveData<ResultLoader<Meals<Product>>>()
    val mealsLiveData: LiveData<ResultLoader<Meals<Product>>> =
        _mealsLiveData


    init {
        getProducts()
        getCategories()
    }
    fun getCategories(){
        _categoriesLiveData.loadData {
            getCategoriesUseCase()
        }
    }

    fun getProducts(){
        _mealsLiveData.loadData {
            getProductsUseCase()
        }
    }
    fun getProductByCategory(category : String) : List<Product>{
        return when(val meals = mealsLiveData.value){
            is ResultLoader.Success->{
                meals.value.meals.filter {
                    it.strCategory ==category
                }
            }
            else ->{
                listOf()
            }
        }
    }
}