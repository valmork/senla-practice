package com.example.senlapractice.presentation.navigation

import androidx.compose.runtime.Stable
import kotlinx.serialization.Serializable

@Stable
@Serializable
sealed class Screen {

    @Stable
    @Serializable
    data object MovieList : Screen()
}