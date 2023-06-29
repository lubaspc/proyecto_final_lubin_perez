package com.example.proyectofinal

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinal.ui.ScreenFavorite
import com.example.proyectofinal.ui.ScreenMain
import com.example.proyectofinal.ui.theme.ProyectoFinalTheme
import com.example.proyectofinal.utils.Routes

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    private val vm: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.getDogsService()
        initObservers()
        setContent {
            ProyectoFinalTheme {
                ConfigureNavigation()
            }
        }
    }

    private fun initObservers() {
        vm.breedSaved.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        vm.errorState.observe(this) {
            if (it.isNullOrBlank()) return@observe
            AlertDialog.Builder(this)
                .setTitle("Ocurrio un error")
                .setMessage(it)
                .setPositiveButton("Aceptar") { _, _ ->
                    vm.errorState.value = null
                }
                .setOnCancelListener {
                    vm.errorState.value = null
                }.show()

        }
    }

    @Composable
    private fun ConfigureNavigation() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Routes.ScreenMain) {
            composable(Routes.ScreenMain) {
                ScreenMain(navController, vm)
            }
            composable(Routes.ScreenFavorite) {
                ScreenFavorite(vm)
            }

        }
    }


}

