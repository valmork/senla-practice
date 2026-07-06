package com.example.senlapractice.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.senlapractice.presentation.movielist.MovieListScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.MovieList,
        modifier = modifier
    ) {
        composable<Screen.MovieList> {
            MovieListScreen()
        }
    }
}