package com.example.proyectofinal.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Breed::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dogDao(): DogDao

    companion object{
        lateinit var db: AppDatabase
        @JvmStatic
        fun initialDB(context: Context){
            if (this::db.isInitialized) return
            synchronized(this){
                db = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java, "database-app"
                ).build()
            }
        }
    }
}