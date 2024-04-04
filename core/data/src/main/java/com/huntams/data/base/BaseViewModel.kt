package com.huntams.data.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    fun <T> MutableLiveData<ResultLoader<T>>.loadData(
        block: suspend () -> T,
    ){
        viewModelScope.launch {
            flow {
                try {
                    emit(ResultLoader.Loading())
                    emit(ResultLoader.Success(block()))
                } catch (t: Throwable) {
                    emit(ResultLoader.Failure(t))
                }
            }.collect { result ->
                postValue(result)
            }
        }
    }
}