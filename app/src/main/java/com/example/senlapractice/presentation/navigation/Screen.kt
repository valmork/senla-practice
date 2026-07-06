package com.example.senlapractice.presentation.navigation

sealed class Screen(val route: String) {
    data object MovieList : Screen("movie_list")
}