package com.example.senlapractice.presentation.moviedetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.core.ui.theme.ExtendedTheme
import com.example.core.ui.theme.SenlaPracticeTheme
import com.example.senlapractice.R
import com.example.senlapractice.domain.model.Movie

@Composable
fun MovieDetailsScreen(
    movie: Movie,
    viewModel: MovieDetailsViewModel = hiltViewModel(
        creationCallback = { factory: MovieDetailsViewModel.Factory ->
            factory.create(movie)
        }
    )
) {
    val state by viewModel.state.collectAsState()
    val currentMovie = state.movie
    val isFavorite = state.isFavorite

    val uiMovie = MovieDetailsUiMapper().map(currentMovie, isFavorite)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f)
        ) {
            AsyncImage(
                model = uiMovie.posterUrl,
                contentDescription = uiMovie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Surface(
                shape = CircleShape,
                color = ExtendedTheme.colors.posterOverlayScrim,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
            ) {
                IconButton(onClick = { viewModel.onFavoriteClick() }) {
                    Icon(
                        imageVector = when (uiMovie.favoriteButton.icon) {
                            FavoriteIcon.Filled -> Icons.Filled.Favorite
                            FavoriteIcon.Outlined -> Icons.Outlined.FavoriteBorder
                        },
                        contentDescription = stringResource(uiMovie.favoriteButton.contentDescriptionRes),
                        tint = when (uiMovie.favoriteButton.tint) {
                            FavoriteTint.Favorite -> ExtendedTheme.colors.favorite
                            FavoriteTint.OnPosterOverlay -> ExtendedTheme.colors.onPosterOverlay
                        },
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Text(
                text = uiMovie.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.padding(top = 8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = uiMovie.releaseYear.ifBlank { stringResource(R.string.date_unknown) },
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = stringResource(R.string.rating),
                        tint = ExtendedTheme.colors.ratingStar
                    )
                    Text(
                        text = uiMovie.rating,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.padding(top = 16.dp))

            if (uiMovie.genres.isNotEmpty()) {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    uiMovie.genres.forEach { genre ->
                        GenreChip(genre)
                    }
                }

                Spacer(modifier = Modifier.padding(top = 20.dp))
            }

            Text(
                text = stringResource(R.string.description),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.padding(top = 8.dp))

            Text(
                text = uiMovie.overview.ifBlank { stringResource(R.string.description_is_missing) },
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun GenreChip(text: String) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

// Preview

@Preview(showBackground = true)
@Composable
private fun MovieDetailsScreenPreview() {
    SenlaPracticeTheme {
        MovieDetailsScreen(
            movie = Movie(
                id = 27205,
                title = stringResource(R.string.preview_title),
                overview = stringResource(R.string.preview_overview),
                releaseDate = stringResource(R.string.preview_release_date),
                posterUrl = null,
                voteAverage = 8.4,
                genres = listOf("Боевик", "Фантастика", "Приключения")
            )
        )
    }
}

