package com.pedrogomez.spacelensapp.models.api

import com.pedrogomez.spacelensapp.models.view.ProductItem

data class ProductsList(
    val code: String,
    val message_error: String,
    val products: List<Product>
)