package com.example.senlapractice.domain.usecase

import com.example.senlapractice.data.MoviesResponseDto
import com.example.senlapractice.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<Result<MoviesResponseDto>> {
        return repository.getPopularMovies()
    }
}