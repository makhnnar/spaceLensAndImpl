package com.pedrogomez.spacelensapp.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pedrogomez.spacelensapp.models.api.Product
import com.pedrogomez.spacelensapp.models.api.toPresentationModel
import com.pedrogomez.spacelensapp.models.view.ProductItem
import com.pedrogomez.spacelensapp.repository.ProductosRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import com.pedrogomez.spacelensapp.models.result.Result
import com.pedrogomez.spacelensapp.models.view.Location

class SharedProductsViewModel(
    private val productosRepository: ProductosRepository
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
            val productList = productosRepository.getProductsData(
                Location(1,1)
            )
            productsListStateApi.postValue(
                    if(productList!=null){
                        Result.Success(true)
                    }else{
                        Result.Error.RecoverableError("Check your internet connexion")
                    }
            )
            productsListLiveData.postValue(
                productList?.products?.map(
                    Product::toPresentationModel
                )
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

}