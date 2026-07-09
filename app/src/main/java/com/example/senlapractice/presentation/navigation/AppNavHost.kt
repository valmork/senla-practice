package com.example.senlapractice.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.senlapractice.domain.model.Movie
import com.example.senlapractice.presentation.favorites.FavoritesScreen
import com.example.senlapractice.presentation.moviedetails.MovieDetailsScreen
import com.example.senlapractice.presentation.movielist.MovieListScreen
import kotlin.reflect.typeOf

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
            MovieListScreen(
                onMovieClick = { movie ->
                    navController.navigate(Screen.MovieDetails(movie))
                }
            )
        }
        composable<Screen.Favorites> {
            FavoritesScreen(
                onMovieClick = { movie ->
                    navController.navigate(Screen.MovieDetails(movie))
                }
            )
        }
        composable<Screen.MovieDetails>(
            typeMap = mapOf(typeOf<Movie>() to MovieNavType)
        ) { backStackEntry ->
            val args: Screen.MovieDetails = backStackEntry.toRoute()
            MovieDetailsScreen(movie = args.movie)
        }
    }
}