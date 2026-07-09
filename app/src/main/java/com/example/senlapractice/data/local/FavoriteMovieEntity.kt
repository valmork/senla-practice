package com.example.senlapractice.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class FavoriteMovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String?,
    val posterUrl: String?,
    val voteAverage: Double,
    val genres: List<String>
)