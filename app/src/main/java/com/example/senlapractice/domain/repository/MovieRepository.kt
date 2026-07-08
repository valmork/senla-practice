package com.example.senlapractice.domain.repository

import com.example.senlapractice.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(): Flow<Result<List<Movie>>>
}