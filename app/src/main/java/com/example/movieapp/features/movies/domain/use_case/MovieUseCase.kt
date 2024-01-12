package com.example.movieapp.features.movies.domain.use_case

import com.example.movieapp.common.ApiState
import com.example.movieapp.common.map
import com.example.movieapp.data.model.Movies
import com.example.movieapp.features.movies.domain.mapper.MovieMapper
import com.example.movieapp.features.movies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val mapper: MovieMapper
) {
    suspend fun getMovies(): Flow<ApiState<List<Movies.Result>?>> {
        return repository.getMovies().map { results ->
            results.map {
                mapper.mapFrom(it)
            }
        }
    }
}