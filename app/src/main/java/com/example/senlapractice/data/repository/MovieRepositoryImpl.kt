package com.example.senlapractice.data.repository

import com.example.senlapractice.data.MovieApi
import com.example.senlapractice.data.MoviesResponseDto
import com.example.senlapractice.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieRepository {

    override fun getPopularMovies(): Flow<Result<MoviesResponseDto>> = flow {
        val result = try {
            Result.success(movieApi.getPopularMovies())
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }
}