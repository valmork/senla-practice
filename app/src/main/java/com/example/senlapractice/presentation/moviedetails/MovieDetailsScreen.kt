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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.core.ui.theme.SenlaPracticeTheme
import com.example.senlapractice.domain.model.Movie

@Composable
fun MovieDetailsScreen(movie: Movie) {
    var isFavorite by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Постер + кнопка избранного
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f)
        ) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Surface(
                shape = CircleShape,
                color = Color.Black.copy(alpha = 0.4f),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
            ) {
                IconButton(onClick = { isFavorite = !isFavorite }) {
                    Icon(
                        imageVector = if (isFavorite) {
                            Icons.Filled.Favorite
                        } else {
                            Icons.Outlined.FavoriteBorder
                        },
                        contentDescription = if (isFavorite) {
                            "Убрать из избранного"
                        } else {
                            "Добавить в избранное"
                        },
                        tint = if (isFavorite) Color(0xFFE53935) else Color.White,
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
                text = movie.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(top = 8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = movie.releaseDate?.take(4) ?: "Дата неизвестна",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Рейтинг",
                        tint = Color(0xFFFFC107)
                    )
                    Text(
                        text = "%.1f".format(movie.voteAverage),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(top = 16.dp))

            if (movie.genres.isNotEmpty()) {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    movie.genres.forEach { genre ->
                        GenreChip(genre)
                    }
                }

                androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(top = 20.dp))
            }

            Text(
                text = "Описание",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(top = 8.dp))

            Text(
                text = movie.overview.ifBlank { "Описание отсутствует" },
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
                title = "Начало",
                overview = "Кобб — талантливый вор, лучший из лучших в опасном искусстве " +
                        "извлечения: он крадёт ценные секреты из глубин подсознания во время " +
                        "сна, когда человеческий разум наиболее уязвим.",
                releaseDate = "2010-07-15",
                posterUrl = null,
                voteAverage = 8.4,
                genres = listOf("Боевик", "Фантастика", "Приключения")
            )
        )
    }
}