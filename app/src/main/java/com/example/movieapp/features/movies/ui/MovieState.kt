package com.example.movieapp.features.movies.ui

import com.example.movieapp.data.model.Movies

data class MovieState(
    val data: List<Movies.Result> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false
)
