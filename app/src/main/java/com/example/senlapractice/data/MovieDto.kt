package com.example.senlapractice.data

data class MoviesResponseDto(
    val results: List<MovieDto>
)

data class MovieDto(
    val id: Int,
    val title: String,
    val poster_path: String?
)