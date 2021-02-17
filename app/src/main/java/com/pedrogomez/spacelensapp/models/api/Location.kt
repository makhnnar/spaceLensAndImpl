package com.pedrogomez.spacelensapp.models.api

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val latitude: Double,
    val longitude: Double
)