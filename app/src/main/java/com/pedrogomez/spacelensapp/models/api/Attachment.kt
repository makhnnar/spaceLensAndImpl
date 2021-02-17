package com.pedrogomez.spacelensapp.models.api

import kotlinx.serialization.Serializable

@Serializable
data class Attachment(
    val thumbnail: String,
    val type: String,
    val url: String
)