package com.pedrogomez.spacelensapp.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pedrogomez.spacelensapp.models.api.Product
import com.pedrogomez.spacelensapp.models.api.toPresentationModel
import com.pedrogomez.spacelensapp.models.view.ProductItem
import com.pedrogomez.spacelensapp.repository.ProductsApiRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import com.pedrogomez.spacelensapp.models.result.Result
import com.pedrogomez.spacelensapp.models.view.Location

class SharedProductsViewModel(
    private val productsApiRepository: ProductsRepository
) : ViewModel(){

    val scope : CoroutineScope = CoroutineScope(
        Dispatchers.IO
    )

    val productsListLiveData = MutableLiveData<List<ProductItem>>()

    val findedProductLiveData = MutableLiveData<ProductItem>()

    val productsListStateApi = MutableLiveData<Result>()

    init {
        productsListLiveData.postValue(ArrayList<ProductItem>())
    }

    fun loadContent(){
        productsListStateApi.postValue(
            Result.LoadingMoreContent(true)
        )
        scope.launch {
            val productList = productsApiRepository.loadProducts(
                    Location(1,1)
            )
            productsListStateApi.postValue(
                Result.Success(true)
            )
            productsListLiveData.postValue(
                    productList
            )
        }

    }

    fun saveFindedProduct(productItem: ProductItem){
        findedProductLiveData.value = productItem
    }

    override fun onCleared() {
        super.onCleared()
        scope.coroutineContext.cancelChildren()
    }

    interface ProductsRepository{

        suspend fun loadProducts(location:Location):List<ProductItem>

    }

}