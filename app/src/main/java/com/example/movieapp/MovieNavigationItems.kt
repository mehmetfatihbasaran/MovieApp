package com.example.movieapp

sealed class MovieNavigationItems(val route:String){

    object MovieList : MovieNavigationItems("movielist")
    object MovieDetails : MovieNavigationItems("movieDetails")

}
