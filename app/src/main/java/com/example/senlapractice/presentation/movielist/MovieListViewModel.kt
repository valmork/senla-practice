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
import kotlinx.coroutines.launch
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
                    onSuccess = { response ->
                        _state.update {
                            it.copy(isLoading = false, movies = response.results)
                        }
                    },
                    onFailure = { throwable ->
                        _state.update {
                            it.copy(isLoading = false, error = throwable.message ?: "Unknown error")
                        }
                    }
                )
            }
            .launchIn(viewModelScope)
    }
}