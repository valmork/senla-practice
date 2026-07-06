package com.example.senlapractice.domain.repository

import com.example.senlapractice.data.MoviesResponseDto
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(): Flow<Result<MoviesResponseDto>>
}