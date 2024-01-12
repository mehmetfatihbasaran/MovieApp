package com.example.movieapp.data.model

data class Movies(
    val page: Int?,
    val results: List<Result>,
){
    data class Result(
        val id: Long? = 0,
        val original_title: String? = "",
        val overview: String? = "",
        val poster_path: String? = "",
        val vote_average: Float? = 0.0f,
    )
}
