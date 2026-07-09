package com.example.senlapractice.presentation.moviedetails

import com.example.senlapractice.domain.model.Movie

data class MovieDetailsState(
    val movie: Movie,
    val isFavorite: Boolean = false
)