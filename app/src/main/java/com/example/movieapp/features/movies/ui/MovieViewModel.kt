package com.example.movieapp.features.movies.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.common.doOnFailure
import com.example.movieapp.common.doOnLoading
import com.example.movieapp.common.doOnSuccess
import com.example.movieapp.data.model.Movies
import com.example.movieapp.features.movies.domain.use_case.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val useCase: MovieUseCase
) : ViewModel() {

    private val _res: MutableState<MovieState> = mutableStateOf(MovieState())
    val res: State<MovieState> = _res

    private val _movieDetails:MutableState<Movies.Result> = mutableStateOf(Movies.Result())
    val movieDetails:MutableState<Movies.Result> = _movieDetails

    fun setMovie(data:Movies.Result){
        _movieDetails.value = data
    }

    init {
        viewModelScope.launch {
            useCase.getMovies()
                .doOnSuccess {
                    _res.value = MovieState(data = it!!)
                }.doOnFailure {
                    _res.value = MovieState(error = it.message!!)
                }.doOnLoading {
                    _res.value = MovieState(isLoading = true)
                }.collect()
        }
    }

}

