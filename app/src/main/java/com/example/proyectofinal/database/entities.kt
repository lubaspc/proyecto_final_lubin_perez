package com.example.proyectofinal.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(primaryKeys = ["file","breed"])
data class Breed(
    @ColumnInfo(name = "file") val file: String,
    @ColumnInfo(name = "breed") val BreedId: String
)