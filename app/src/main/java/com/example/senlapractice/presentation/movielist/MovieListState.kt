package com.example.senlapractice.presentation.movielist

import com.example.senlapractice.domain.model.Movie

data class MovieListState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String? = null
)

sealed interface MovieListIntent {
    data object LoadMovies : MovieListIntent
}