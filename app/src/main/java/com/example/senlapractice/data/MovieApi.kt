package com.example.senlapractice.data

import retrofit2.http.GET

interface MovieApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(): MoviesResponseDto
}