package com.example.senlapractice.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.senlapractice.presentation.favorites.FavoritesScreen
import com.example.senlapractice.presentation.movielist.MovieListScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.MovieList,
        modifier = modifier
    ) {
        composable<Screen.MovieList> {
            MovieListScreen()
        }
        composable<Screen.Favorites> {
            FavoritesScreen()
        }
    }
}