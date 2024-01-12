package com.example.movieapp.data.repository

import com.example.movieapp.common.ApiState
import com.example.movieapp.common.base.BaseRepository
import com.example.movieapp.data.model.Movies
import com.example.movieapp.data.network.ApiService
import com.example.movieapp.features.movies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val apiService: ApiService) : MovieRepository, BaseRepository() {
    override suspend fun getMovies(): Flow<ApiState<Movies>> = safeApiCall{
        apiService.getMovies()
    }

}