package com.pedrogomez.spacelensapp.models.api

import com.pedrogomez.spacelensapp.models.view.ProductItem
import kotlinx.serialization.Serializable

@Serializable
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
    val lat: Long,
    val like_user: Boolean,
    val likes: Int,
    val location: Location,
    val lon: Long,
    val offer: Boolean,
    val owner: String,
    val p_condition: String,
    val payment_method: String?,
    val price: Long,
    val product_id: Int,
    val rating_amount: Int,
    val rating_product: Int,
    val story_img: String?,
    val story_url: String?,
    val title: String
)

fun Product.toPresentationModel() : ProductItem {
    return ProductItem(
        product_id,
        likes,
        address,
        price,
        currency,
        p_condition,
        category,
        title,
        description,
        attachment.url,
        attachment.thumbnail,
        created,
        owner
    )
}