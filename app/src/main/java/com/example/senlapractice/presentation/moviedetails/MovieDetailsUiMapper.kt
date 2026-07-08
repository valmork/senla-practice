package com.example.senlapractice.presentation.moviedetails

import com.example.senlapractice.R
import com.example.senlapractice.domain.model.Movie
import java.util.Locale

class MovieDetailsUiMapper {

    fun map(movie: Movie, isFavorite: Boolean): UiMovie {
        return UiMovie(
            title = movie.title,
            overview = movie.overview,
            posterUrl = movie.posterUrl,
            releaseYear = movie.releaseDate?.take(4).orEmpty(),
            rating = String.format(Locale.US, "%.1f", movie.voteAverage),
            genres = movie.genres,
            favoriteButton = mapFavoriteButton(isFavorite)
        )
    }

    private fun mapFavoriteButton(isFavorite: Boolean): FavoriteButtonUi {
        return if (isFavorite) {
            FavoriteButtonUi(
                icon = FavoriteIcon.Filled,
                contentDescriptionRes = R.string.delete_from_favourites,
                tint = FavoriteTint.Favorite
            )
        } else {
            FavoriteButtonUi(
                icon = FavoriteIcon.Outlined,
                contentDescriptionRes = R.string.add_to_favourites,
                tint = FavoriteTint.OnPosterOverlay
            )
        }
    }
}