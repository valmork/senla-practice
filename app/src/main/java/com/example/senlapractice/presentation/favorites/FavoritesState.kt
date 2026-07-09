package com.example.senlapractice.presentation.favorites

import com.example.senlapractice.domain.model.Movie

data class FavoritesState(
    val movies: List<Movie> = emptyList()
)