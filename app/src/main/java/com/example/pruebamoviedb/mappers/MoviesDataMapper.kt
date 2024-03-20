package com.example.pruebamoviedb.mappers

import com.example.pruebamoviedb.data.sources.local.MovieEntity
import com.example.pruebamoviedb.data.sources.remote.DTO.ListMoviesDTO
import com.example.pruebamoviedb.data.sources.remote.DTO.MoviesResultDTO
import com.example.pruebamoviedb.domain.models.MoviesResult
import javax.inject.Inject

/**
 * Extension function to convert MoviesResultDTO to MovieEntity.
 * This function maps the properties of MoviesResultDTO to the properties of MovieEntity.
 * If any property in MoviesResultDTO is null, a default value is used.
 *
 * @param category The category of the movie.
 * @return A MovieEntity object with the properties set from MoviesResultDTO.
 */
fun MoviesResultDTO.toMovieEntity(
    category: String
): MovieEntity {
    return MovieEntity(
        adult = adult ?: false,
        backdrop_path = backdrop_path ?: "",
        original_language = original_language ?: "",
        overview = overview ?: "",
        poster_path = poster_path ?: "",
        release_date = release_date ?: "",
        title = title ?: "",
        vote_average = vote_average ?: 0.0,
        popularity = popularity ?: 0.0,
        vote_count = vote_count ?: 0,
        id = id ?: -1,
        original_title = original_title ?: "",
        video = video ?: false,

        category = category,

        genre_ids = try {
            genre_ids?.joinToString(",") ?: "-1,-2"
        } catch (e: Exception) {
            "-1,-2"
        }
    )
}

/**
 * Extension function to convert MovieEntity to MoviesResult.
 * This function maps the properties of MovieEntity to the properties of MoviesResult.
 *
 * @param category The category of the movie.
 * @return A MoviesResult object with the properties set from MovieEntity.
 */
fun MovieEntity.toMovie(
    category: String
): MoviesResult {
    return MoviesResult(
        backdrop_path = backdrop_path,
        original_language = original_language,
        overview = overview,
        poster_path = poster_path,
        release_date = release_date,
        title = title,
        vote_average = vote_average,
        popularity = popularity,
        vote_count = vote_count,
        video = video,
        id = id,
        adult = adult,
        original_title = original_title,

        category = category,

        genre_ids = try {
            genre_ids.split(",").map { it.toInt() }
        } catch (e: Exception) {
            listOf(-1, -2)
        }
    )
}