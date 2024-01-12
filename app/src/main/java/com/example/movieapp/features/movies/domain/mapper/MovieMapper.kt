package com.example.movieapp.features.movies.domain.mapper

import com.example.movieapp.common.base.Mapper
import com.example.movieapp.data.model.Movies
import javax.inject.Inject

class MovieMapper @Inject constructor() : Mapper<Movies?, List<Movies.Result>?> {
    override fun mapFrom(from: Movies?): List<Movies.Result>? {
        return from?.results?.map {
            Movies.Result(
                id = it.id,
                original_title = it.original_title,
                overview = it.overview,
                poster_path = it.poster_path,
                vote_average = it.vote_average
            )
        }
    }
}