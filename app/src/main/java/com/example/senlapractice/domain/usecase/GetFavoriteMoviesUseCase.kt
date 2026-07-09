package com.example.senlapractice.domain.usecase

import com.example.senlapractice.domain.model.Movie
import com.example.senlapractice.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteMoviesUseCase @Inject constructor(
    private val repository: FavoritesRepository
) {
    operator fun invoke(): Flow<List<Movie>> = repository.getFavorites()
}