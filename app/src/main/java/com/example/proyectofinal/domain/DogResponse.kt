package com.example.proyectofinal.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DogResponse(
    @field:Json(name = "message") var message: List<String>,
    @field:Json(name = "status")var status: String
)