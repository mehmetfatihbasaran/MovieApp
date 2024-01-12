package com.example.movieapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.movieapp.MovieNavigationItems
import com.example.movieapp.R
import com.example.movieapp.data.model.Movies
import com.example.movieapp.data.network.ApiService
import com.example.movieapp.features.movies.ui.MovieViewModel

@Composable
fun MovieScreen(
    navController: NavController,
    viewModel: MovieViewModel = hiltViewModel()
) {

    val res = viewModel.res.value
    if (res.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Center
        ) {
            CircularProgressIndicator()
        }
    }
    if (res.error.isNotEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Center) {
            Text(
                text = res.error,
            )
        }
    }
    if (res.data.isNotEmpty()) {
        val movies = res.data
        LazyColumn {
            items(
                items = movies,
                key = { it.id!! }
            ) {
                MovieList(res = it){
                    viewModel.setMovie(it)
                    navController.navigate(MovieNavigationItems.MovieDetails.route + "/${it.id}")
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieList(res: Movies.Result, onGettingClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        elevation = 2.dp,
        backgroundColor = Color.White,
        onClick = { onGettingClick }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            val image = rememberAsyncImagePainter(
                model = ImageRequest.Builder(
                    context = LocalContext.current,
                )
                    .data("${ApiService.IMAGE_URL}${res.poster_path}")
                    .placeholder(R.drawable.ic_launcher_background)
                    .crossfade(true)
                    .transformations(CircleCropTransformation())
                    .build()
            )

            Image(
                painter = image,
                contentDescription = "image",
                modifier = Modifier
                    .size(100.dp)
                    .padding(10.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(CenterVertically)
            ) {
                Text(
                    text = res.original_title!!, style = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = res.overview!!, style = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp
                    ),
                    textAlign = TextAlign.Center
                )
            }

        }

    }
}