package com.example.senlapractice.data.repository

import com.example.senlapractice.data.MovieApi
import com.example.senlapractice.data.TmdbConfig
import com.example.senlapractice.domain.model.Movie
import com.example.senlapractice.domain.repository.MovieRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieRepository {

    override fun getPopularMovies(): Flow<Result<List<Movie>>> = flow {
        val result = try {
            coroutineScope {
                val genresDeferred = async { movieApi.getGenres().genres }
                val pagesDeferred = (1..TOTAL_PAGES).map { page ->
                    async { movieApi.getPopularMovies(page = page).results }
                }

                val genresById = genresDeferred.await().associateBy { it.id }
                val movieDtos = pagesDeferred.awaitAll().flatten()
                    .distinctBy { it.id }

                val movies = movieDtos.map { dto ->
                    Movie(
                        id = dto.id,
                        title = dto.title,
                        overview = dto.overview,
                        releaseDate = dto.release_date,
                        posterUrl = TmdbConfig.buildPosterUrl(dto.poster_path),
                        voteAverage = dto.vote_average,
                        genres = dto.genre_ids.mapNotNull { genresById[it]?.name }
                    )
                }

                Result.success(movies)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }

        emit(result)
    }

    companion object {
        private const val TOTAL_PAGES = 15 // 20 фильмов * 15 страниц = 300
    }
}