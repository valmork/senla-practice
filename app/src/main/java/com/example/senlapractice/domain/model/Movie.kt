package com.example.senlapractice.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String?,
    val posterUrl: String?,
    val voteAverage: Double,
    val genres: List<String>
)