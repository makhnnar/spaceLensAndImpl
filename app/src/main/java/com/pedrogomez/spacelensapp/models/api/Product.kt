package com.pedrogomez.spacelensapp.models.api

data class Product(
    val address: String,
    val attachment: Attachment,
    val category: String,
    val created: String,
    val createdTime: String,
    val currency: String,
    val description: String,
    val distance: Int,
    val is_private: String,
    val lat: Int,
    val like_user: Boolean,
    val likes: Int,
    val location: Location,
    val lon: Int,
    val offer: Boolean,
    val owner: String,
    val p_condition: String,
    val payment_method: String,
    val price: Int,
    val product_id: Int,
    val rating_amount: Int,
    val rating_product: Int,
    val story_img: Any,
    val story_url: Any,
    val title: String
)