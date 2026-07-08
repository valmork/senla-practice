package com.example.senlapractice.presentation.moviedetails

data class UiMovie(
    val title: String,
    val overview: String,
    val posterUrl: String?,
    val releaseYear: String,
    val rating: String,
    val genres: List<String>,
    val favoriteButton: FavoriteButtonUi
)

data class FavoriteButtonUi(
    val icon: FavoriteIcon,
    val contentDescriptionRes: Int,
    val tint: FavoriteTint
)

enum class FavoriteIcon {
    Filled,
    Outlined
}

enum class FavoriteTint {
    Favorite,
    OnPosterOverlay
}