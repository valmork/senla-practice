package com.example.senlapractice.data.repository

import com.example.senlapractice.data.local.FavoriteMovieDao
import com.example.senlapractice.data.local.FavoriteMovieEntity
import com.example.senlapractice.domain.model.Movie
import com.example.senlapractice.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val dao: FavoriteMovieDao
) : FavoritesRepository {

    override fun getFavorites(): Flow<List<Movie>> {
        return dao.getAllFavorites().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun isFavorite(movieId: Int): Flow<Boolean> {
        return dao.isFavorite(movieId)
    }

    override suspend fun addToFavorites(movie: Movie) {
        dao.insert(movie.toEntity())
    }

    override suspend fun removeFromFavorites(movieId: Int) {
        dao.deleteById(movieId)
    }

    private fun Movie.toEntity(): FavoriteMovieEntity {
        return FavoriteMovieEntity(
            id = id,
            title = title,
            overview = overview,
            releaseDate = releaseDate,
            posterUrl = posterUrl,
            voteAverage = voteAverage,
            genres = genres
        )
    }

    private fun FavoriteMovieEntity.toDomain(): Movie {
        return Movie(
            id = id,
            title = title,
            overview = overview,
            releaseDate = releaseDate,
            posterUrl = posterUrl,
            voteAverage = voteAverage,
            genres = genres
        )
    }
}