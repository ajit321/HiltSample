package com.hilt.hiltsample.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hilt.hiltsample.data.repository.ProductRepository
import com.hilt.hiltsample.model.ProductsModel
import com.hilt.hiltsample.utils.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainViewModel @ViewModelInject constructor(private val postsRepository: ProductRepository) :
    ViewModel() {

    private val _postsLiveData = MutableLiveData<State<ProductsModel>>()

    val postsLiveData: LiveData<State<ProductsModel>>
        get() = _postsLiveData

    /*fun getAllProducts() {
        viewModelScope.launch {
            postsRepository.getAllProducts().collect {
                _postsLiveData.value = it
            }
        }
    }*/

    fun getAllProducts() {
        viewModelScope.launch {
            postsRepository.getAllProducts().collect {
                _postsLiveData.value = it
            }
        }
    }
}