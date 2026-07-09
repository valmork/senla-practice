package com.example.senlapractice.presentation.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.senlapractice.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MovieListState())
    val state: StateFlow<MovieListState> = _state.asStateFlow()

    init {
        handleIntent(MovieListIntent.LoadMovies)
    }

    fun handleIntent(intent: MovieListIntent) {
        when (intent) {
            is MovieListIntent.LoadMovies -> loadMovies()
        }
    }

    private fun loadMovies() {
        _state.update { it.copy(isLoading = true, error = null) }

        getPopularMoviesUseCase()
            .onEach { result ->
                result.fold(
                    onSuccess = { movies ->
                        _state.update { it.copy(isLoading = false, movies = movies) }
                    },
                    onFailure = { throwable ->
                        val errorMessage = when (throwable) {
                            is IOException -> "Нет подключения к интернету. Проверьте соединение и попробуйте снова."
                            else -> "Не удалось загрузить фильмы: ${throwable.message}"
                        }
                        _state.update { it.copy(isLoading = false, error = errorMessage) }
                    }
                )
            }
            .launchIn(viewModelScope)
    }
}