package com.example.movieapp.data.network

import com.example.movieapp.data.model.Movies
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    companion object {
        const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w500"
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    }

    @GET("discover/movie?api_key=5d97f7b5c798641dd558bc1a4b095d92")
    suspend fun getMovies(): Response<Movies>

}