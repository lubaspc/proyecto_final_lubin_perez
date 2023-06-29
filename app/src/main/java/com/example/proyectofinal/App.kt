package com.example.proyectofinal

import android.app.Application
import com.example.proyectofinal.database.AppDatabase

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        AppDatabase.initialDB(this)
    }
}