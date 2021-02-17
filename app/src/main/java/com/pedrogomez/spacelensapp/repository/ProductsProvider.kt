package com.pedrogomez.spacelensapp.repository

import com.pedrogomez.spacelensapp.models.api.Product
import com.pedrogomez.spacelensapp.models.api.toPresentationModel
import com.pedrogomez.spacelensapp.models.view.Location
import com.pedrogomez.spacelensapp.models.view.ProductItem
import com.pedrogomez.spacelensapp.view.viewmodel.SharedProductsViewModel

/**
 * this class is for get which repo is going to be consumed
 * */
class ProductsProvider(
        private val productsApiRepository: ProductsApiRepository
    ) : SharedProductsViewModel.ProductsRepository {

    override suspend fun loadProducts(location: Location): List<ProductItem> {
        val response = productsApiRepository.getProductsData(location)
        return response?.products?.map(Product::toPresentationModel) ?: ArrayList<ProductItem>()
    }

}