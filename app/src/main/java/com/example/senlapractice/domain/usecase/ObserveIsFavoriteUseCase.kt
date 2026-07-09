package com.example.senlapractice.domain.usecase

import com.example.senlapractice.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveIsFavoriteUseCase @Inject constructor(
    private val repository: FavoritesRepository
) {
    operator fun invoke(movieId: Int): Flow<Boolean> = repository.isFavorite(movieId)
}