package com.example.proyectofinal.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.proyectofinal.model.Dog
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DogCard(
    dog: Dog,
    icon: ImageVector,
    onClickIcon: (String) -> Unit
) {
    Card(Modifier.fillMaxWidth()) {
        Box(modifier = Modifier.padding(12.dp)) {
            Text(
                text = dog.name,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }
        AutoSlidingCarousel(itemsCount = dog.images.size) { i ->
            Box(modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(dog.images[i])
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.height(400.dp)
                )
                Surface(
                    modifier = Modifier
                        .align(BiasAlignment(0.98f, -0.98f)),
                    shape = CircleShape,
                ) {
                    IconButton(onClick = { onClickIcon(dog.images[i]) }) {
                        Icon(
                            imageVector = icon,
                            contentDescription = ""
                        )
                    }
                }
            }

        }
    }
}


@Preview
@Composable
fun DogCardPreview() {
    DogCard(
        Dog("Pastor Aleman", mutableListOf("https://images.dog.ceo/breeds/akita/Akita_Inu_dog.jpg")),
        Icons.Filled.Favorite
    ) {}
}