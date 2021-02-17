package com.pedrogomez.spacelensapp.models.view

import java.io.Serializable

data class ProductItem(
    val product_id:Int,
    val likes:Int,
    val address:String,
    val price:Long,
    val currency:String,
    val p_condition:String,
    val category:String,
    val title:String,
    val description:String,
    val fullImag:String,
    val thumbnail:String,
    val created:String,
    val owner:String
) : Serializable