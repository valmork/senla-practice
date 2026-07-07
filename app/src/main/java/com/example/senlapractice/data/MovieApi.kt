package com.example.senlapractice.data

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = "ru-RU",
        @Query("page") page: Int = 1
    ): MoviesResponseDto

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("language") language: String = "ru-RU"
    ): GenreListResponseDto
}