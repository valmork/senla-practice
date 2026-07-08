package com.example.senlapractice.presentation.navigation

import androidx.compose.runtime.Stable
import com.example.senlapractice.domain.model.Movie
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {

    @Stable
    @Serializable
    data object MovieList : Screen()

    @Stable
    @Serializable
    data object Favorites : Screen()

    @Serializable
    data class MovieDetails(val movie: Movie) : Screen()
}