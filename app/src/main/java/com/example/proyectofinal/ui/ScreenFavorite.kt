package com.example.proyectofinal.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyectofinal.ui.components.MyToolbar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenFavorite(){
    Scaffold(topBar = { MyToolbar(false) }){
        Column(Modifier.padding(it)) {

        }
    }
}


@Preview
@Composable
fun ScreenFavoritePreview(){
    ScreenFavorite()
}