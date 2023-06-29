package com.example.proyectofinal.ui

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.proyectofinal.MainViewModel
import com.example.proyectofinal.ui.components.AutoSlidingCarousel
import com.example.proyectofinal.ui.components.DogCard
import com.example.proyectofinal.ui.components.MyToolbar
import com.example.proyectofinal.utils.Routes
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
fun ScreenMain(navController: NavController, vm: MainViewModel) {
    val rememberIsLoading by remember { vm.isLoading }
    val rememberList = remember { vm.dogs }
    val swipeRefresh = rememberSwipeRefreshState(rememberIsLoading)
    val (showSearch, setShowSearch) = remember { mutableStateOf(false) }
    val (textSearch, setTextSearch) = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            MyToolbar(
                onClickFavorite = {
                    navController.navigate(Routes.ScreenFavorite)
                    vm.getDBBreeds()
                }, onClickSearch = {
                    setShowSearch(!showSearch)
                })
        },
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            if (showSearch) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    OutlinedTextField(
                        value = textSearch,
                        onValueChange = { text ->
                            setTextSearch(text)
                            vm.filterList(text)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text(text = "Que deseas buscar") }
                    )
                }
            }
            SwipeRefresh(
                state = swipeRefresh,
                onRefresh = {
                    vm.getDogsService()
                    setTextSearch("")
                    setShowSearch(false)
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                LazyColumn(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(rememberList) { dog ->
                        DogCard(dog = dog, icon = Icons.Outlined.FavoriteBorder) { image ->
                            vm.saveBreed(dog.name, image)
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun ScreenMainPreview() {
    ScreenMain(rememberNavController(), viewModel())
}