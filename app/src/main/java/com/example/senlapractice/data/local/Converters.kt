package com.example.senlapractice.data.local

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromGenresList(genres: List<String>): String {
        return genres.joinToString(separator = "||")
    }

    @TypeConverter
    fun toGenresList(data: String): List<String> {
        if (data.isEmpty()) return emptyList()
        return data.split("||")
    }
}