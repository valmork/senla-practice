package com.example.senlapractice.presentation.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.senlapractice.domain.model.Movie
import com.example.senlapractice.domain.usecase.ObserveIsFavoriteUseCase
import com.example.senlapractice.domain.usecase.ToggleFavoriteUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = MovieDetailsViewModel.Factory::class)
class MovieDetailsViewModel @AssistedInject constructor(
    @Assisted private val movie: Movie,
    private val observeIsFavoriteUseCase: ObserveIsFavoriteUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(movie: Movie): MovieDetailsViewModel
    }

    private val _state = MutableStateFlow(MovieDetailsState(movie = movie))
    val state: StateFlow<MovieDetailsState> = _state.asStateFlow()

    init {
        observeIsFavoriteUseCase(movie.id)
            .onEach { isFavorite ->
                _state.update { it.copy(isFavorite = isFavorite) }
            }
            .launchIn(viewModelScope)
    }

    fun onFavoriteClick() {
        viewModelScope.launch {
            toggleFavoriteUseCase(movie, _state.value.isFavorite)
        }
    }
}