package com.example.movieapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.features.movies.ui.MovieViewModel
import com.example.movieapp.ui.screens.MovieDetailScreen
import com.example.movieapp.ui.screens.MovieScreen

@Composable
fun NavGraph(
    viewModel: MovieViewModel
) {

    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = MovieNavigationItems.MovieList.route
    ) {
        composable(MovieNavigationItems.MovieList.route){
            MovieScreen(viewModel = viewModel, navController = navHostController)
        }
        composable(MovieNavigationItems.MovieDetails.route){
            MovieDetailScreen(viewModel)
        }
    }

}