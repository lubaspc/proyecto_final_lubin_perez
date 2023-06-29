package com.example.proyectofinal.domain

import retrofit2.http.GET
import retrofit2.http.Path

interface DogsService {
    @GET("breeds/list")
    suspend fun getBreeds(): DogResponse

    @GET("breed/{breed}/images/random/{amount}")
    suspend fun getImages(
        @Path("breed") breed: String,
        @Path("amount") amount: Int
    ): DogResponse

}