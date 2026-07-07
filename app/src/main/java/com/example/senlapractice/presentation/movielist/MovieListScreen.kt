package com.example.senlapractice.presentation.movielist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.core.ui.theme.SenlaPracticeTheme
import com.example.senlapractice.data.MovieDto
import com.example.senlapractice.data.TmdbConfig
import com.example.senlapractice.domain.model.Movie

// Контейнер
@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    MovieListContent(state = state)
}

// Чистый UI
@Composable
private fun MovieListContent(state: MovieListState) {
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            state.error != null -> {
                Text(
                    text = "Ошибка: ${state.error}",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        items = state.movies,
                        key = { movie -> movie.id}
                        ) { movie ->
                        MovieCard(movie)
                    }
                }
            }
        }
    }
}

@Composable
private fun MovieCard(movie: Movie) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f / 3f)
            )
            Text(
                text = movie.title,
                style = MaterialTheme.typography.bodyMedium,
                minLines = 2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}

// Preview

@Preview(showBackground = true)
@Composable
private fun MovieListContentPreview() {
    SenlaPracticeTheme {
        MovieListContent(
            state = MovieListState(
                isLoading = false,
                movies = listOf(
                    Movie(
                        id = 1,
                        title = "Название",
                        overview = "Краткое описание фильма для примера.",
                        releaseDate = "2024-05-10",
                        posterUrl = null,
                        voteAverage = 7.5,
                        genres = listOf("Драма")
                    ),
                    Movie(
                        id = 2,
                        title = "Стандартное название",
                        overview = "Ещё одно описание.",
                        releaseDate = "2023-01-15",
                        posterUrl = null,
                        voteAverage = 8.1,
                        genres = listOf("Боевик", "Триллер")
                    ),
                    Movie(
                        id = 3,
                        title = "Очень большое и длинное длинное название",
                        overview = "Описание.",
                        releaseDate = "2022-11-03",
                        posterUrl = null,
                        voteAverage = 6.4,
                        genres = listOf("Комедия")
                    ),
                    Movie(
                        id = 4,
                        title = "ОЧЕНЬ ДЛИННОЕ И ОЧЕНЬ БОЛЬШОЕ НАЗВАНИЕ",
                        overview = "Описание.",
                        releaseDate = "2021-07-20",
                        posterUrl = null,
                        voteAverage = 9.0,
                        genres = listOf("Фантастика", "Приключения")
                    )
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieListContentLoadingPreview() {
    SenlaPracticeTheme {
        MovieListContent(state = MovieListState(isLoading = true))
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieListContentErrorPreview() {
    SenlaPracticeTheme {
        MovieListContent(state = MovieListState(error = "Не удалось загрузить данные"))
    }
}