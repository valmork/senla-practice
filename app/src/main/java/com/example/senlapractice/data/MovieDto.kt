package com.example.senlapractice.data

data class MoviesResponseDto(
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int
)

data class MovieDto(
    val id: Int,
    val title: String,
    val overview: String,
    val release_date: String?,
    val poster_path: String?,
    val vote_average: Double,
    val genre_ids: List<Int>
)