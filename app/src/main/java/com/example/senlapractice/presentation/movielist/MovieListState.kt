package com.example.senlapractice.presentation.movielist

import com.example.senlapractice.data.MovieDto

data class MovieListState(
    val isLoading: Boolean = false,
    val movies: List<MovieDto> = emptyList(),
    val error: String? = null
)

sealed interface MovieListIntent {
    data object LoadMovies : MovieListIntent
}