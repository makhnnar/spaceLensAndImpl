package com.pedrogomez.spacelensapp.repository


import com.pedrogomez.spacelensapp.models.api.ProductsList
import com.pedrogomez.spacelensapp.models.view.Location
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import com.pedrogomez.spacelensapp.utils.extensions.print

class ProductosRepository(
    private val client : HttpClient,
    private val urlBase:String
    ) {

    suspend fun getProductsData(
        location:Location
    ):ProductsList?{
        return try{
            val requestUrl = "${urlBase}latitude=${location.lat}&longitude=${location.lat}"
            "Ktor_request getProductsData: $requestUrl".print()
            val response = client.request<ProductsList>(requestUrl) {
                method = HttpMethod.Get
            }
            "Ktor_request getProductsData: $response".print()
            response
        }catch (e : java.lang.Exception){
            "Ktor_request getProductsData: ${e.message}".print()
            null
        }
    }

}