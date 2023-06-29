package com.example.proyectofinal.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinal.MainViewModel
import com.example.proyectofinal.R
import com.example.proyectofinal.utils.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyToolbar(
    showIcons: Boolean = true,
    subTitle: String = "",
    onClickFavorite: () -> Unit = {},
    onClickSearch: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                "${stringResource(id = R.string.app_name)} $subTitle",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = {
            if (showIcons) {
                IconButton(onClick = onClickFavorite) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Favorito"
                    )
                }
                IconButton(onClick = onClickSearch) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Busqueda"
                    )
                }

            }
        }
    )
}


@Preview
@Composable
fun MyToolbarPreview() {
    MyToolbar()
}