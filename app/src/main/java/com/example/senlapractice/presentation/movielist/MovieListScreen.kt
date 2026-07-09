package com.example.senlapractice.presentation.movielist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.core.ui.theme.SenlaPracticeTheme
import com.example.senlapractice.domain.model.Movie

// Контейнер
@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel = hiltViewModel(),
    onMovieClick: (Movie) -> Unit
) {
    val state by viewModel.state.collectAsState()
    MovieListContent(
        state = state,
        onMovieClick = onMovieClick,
        onRetry = { viewModel.handleIntent(MovieListIntent.LoadMovies) }
    )
}

// Чистый UI
@Composable
private fun MovieListContent(
    state: MovieListState,
    onMovieClick: (Movie) -> Unit,
    onRetry: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            state.error != null -> {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = state.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 32.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = onRetry) {
                        Text("Повторить")
                    }
                }
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
                        key = { movie -> movie.id }
                    ) { movie ->
                        MovieCard(movie = movie, onClick = { onMovieClick(movie) })
                    }
                }
            }
        }
    }
}

@Composable
private fun MovieCard(movie: Movie, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
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
                textAlign = TextAlign.Center,
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
                        overview = "Описание",
                        releaseDate = "2024-05-10",
                        posterUrl = null,
                        voteAverage = 7.5,
                        genres = listOf("Драма")
                    ),
                    Movie(
                        id = 2,
                        title = "Стандартное название",
                        overview = "Описание",
                        releaseDate = "2023-01-15",
                        posterUrl = null,
                        voteAverage = 8.1,
                        genres = listOf("Боевик")
                    )
                )
            ),
            onMovieClick = {},
            onRetry = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieListContentLoadingPreview() {
    SenlaPracticeTheme {
        MovieListContent(state = MovieListState(isLoading = true), onMovieClick = {}, onRetry = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieListContentErrorPreview() {
    SenlaPracticeTheme {
        MovieListContent(
            state = MovieListState(error = "Не удалось загрузить данные"),
            onMovieClick = {},
            onRetry = {}
        )
    }
}