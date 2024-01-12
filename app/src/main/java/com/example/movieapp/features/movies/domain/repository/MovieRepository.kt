package com.example.movieapp.features.movies.domain.repository

import com.example.movieapp.common.ApiState
import com.example.movieapp.data.model.Movies
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getMovies(): Flow<ApiState<Movies>>

}