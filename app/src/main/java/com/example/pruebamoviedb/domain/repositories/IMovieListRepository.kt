package com.example.pruebamoviedb.domain.repositories

import com.example.pruebamoviedb.domain.models.MoviesResult
import com.example.pruebamoviedb.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieListRepository {
    /**
     * Fetches a list of movies.
     * This function can fetch the list from a remote source or a local cache, depending on the parameters.
     *
     * @param forceFetchFromRemote If true, the function fetches the list from a remote source, ignoring the local cache.
     * @param category The category of the movies to fetch.
     * @param page The page number for pagination.
     * @return A Flow that emits the loading status and the list of movies.
     */
    suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ): Flow<Resource<List<MoviesResult>>>

    /**
     * Fetches a movie by its id.
     * This function fetches the movie from a remote source or a local cache, depending on the parameters.
     *
     * @param id The id of the movie to fetch.
     * @return A Flow that emits the loading status and the movie.
     */
    suspend fun getMovie(id: Int): Flow<Resource<MoviesResult>>
}