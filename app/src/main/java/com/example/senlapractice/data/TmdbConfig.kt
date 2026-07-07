package com.example.senlapractice.data

object TmdbConfig {
    private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

    fun buildPosterUrl(posterPath: String?): String? {
        return posterPath?.let { "$IMAGE_BASE_URL$it" }
    }
}