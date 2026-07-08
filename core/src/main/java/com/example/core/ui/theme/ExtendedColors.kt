package com.example.core.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class ExtendedColors(
    val favorite: Color,
    val ratingStar: Color,
    val posterOverlayScrim: Color,
    val onPosterOverlay: Color
)

val LocalExtendedColors = staticCompositionLocalOf {
    ExtendedColors(
        favorite = FavoriteRed,
        ratingStar = RatingStarYellow,
        posterOverlayScrim = PosterOverlayScrim,
        onPosterOverlay = OnPosterOverlay
    )
}