package com.example.pruebamoviedb.data.sources.remote

import com.example.pruebamoviedb.data.sources.remote.DTO.ListMoviesDTO
import com.example.pruebamoviedb.data.sources.remote.DTO.MoviesResultDTO
import com.google.gson.Gson
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(private val moviesAPI: MoviesAPIService) {

    private val gson = Gson()

    suspend fun getMoviesLimit(limit: Int): ListMoviesDTO {
        return moviesAPI.getPopularMovies(limit)
    }

    suspend fun getMoviesTitle(title: String): MoviesResultDTO {
        return moviesAPI.getMovies(title)
    }

}