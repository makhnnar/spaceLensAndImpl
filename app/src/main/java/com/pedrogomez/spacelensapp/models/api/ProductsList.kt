package com.pedrogomez.spacelensapp.models.api

data class ProductsList(
    val code: String,
    val message_error: String,
    val products: List<Product>
)