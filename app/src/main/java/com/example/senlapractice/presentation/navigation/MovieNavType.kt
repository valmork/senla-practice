package com.example.senlapractice.presentation.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.example.senlapractice.domain.model.Movie
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val MovieNavType = object : NavType<Movie>(isNullableAllowed = false) {

    override fun get(bundle: Bundle, key: String): Movie? {
        return bundle.getString(key)?.let {
            Json.decodeFromString(it)
        }
    }

    override fun parseValue(value: String): Movie {
        // декодинг
        return Json.decodeFromString(Uri.decode(value))
    }

    override fun serializeAsValue(value: Movie): String {
        // энкодинг
        return Uri.encode(Json.encodeToString(value))
    }

    override fun put(bundle: Bundle, key: String, value: Movie) {
        bundle.putString(key, Json.encodeToString(value))
    }
}