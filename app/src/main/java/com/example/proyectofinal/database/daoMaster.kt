package com.example.proyectofinal.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface DogDao{
    @Transaction
    @Query("SELECT * FROM Breed")
    fun getBreeds(): List<Breed>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsetBreed(breed: Breed): Long

    @Query("DELETE FROM breed where file = :url and breed = :name")
    fun deleteBreed(name: String,url: String)



}