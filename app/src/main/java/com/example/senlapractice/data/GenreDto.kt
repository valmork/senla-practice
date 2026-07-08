package com.example.senlapractice.data

data class GenreListResponseDto(
    val genres: List<GenreDto>
)

data class GenreDto(
    val id: Int,
    val name: String
)