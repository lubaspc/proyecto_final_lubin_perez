package com.example.proyectofinal.domain

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@JsonClass(generateAdapter = true)
object DogRepository {

    private val BASE_URL = "https://dog.ceo/api/"

    private val api by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .build()
            .create(DogsService::class.java)
    }

    suspend fun getBreeds() = api.getBreeds()

    suspend fun getImages(breed: String,amount: Int = 5) = api.getImages(breed, amount)
}