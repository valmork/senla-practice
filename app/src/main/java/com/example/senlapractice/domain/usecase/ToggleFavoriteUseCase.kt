package com.example.senlapractice.domain.usecase

import com.example.senlapractice.domain.model.Movie
import com.example.senlapractice.domain.repository.FavoritesRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val repository: FavoritesRepository
) {
    suspend operator fun invoke(movie: Movie, isCurrentlyFavorite: Boolean) {
        if (isCurrentlyFavorite) {
            repository.removeFromFavorites(movie.id)
        } else {
            repository.addToFavorites(movie)
        }
    }
}