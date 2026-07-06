package com.example.senlapractice.data

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language")
        language: String = "ru-RU"
    ): MoviesResponseDto
}