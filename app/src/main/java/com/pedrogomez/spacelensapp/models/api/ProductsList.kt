package com.pedrogomez.spacelensapp.models.api

import kotlinx.serialization.Serializable

@Serializable
data class ProductsList(
    val code: String,
    val message_error: String,
    val products: List<Product>
)