package com.example.pruebamoviedb.data.repositories

import com.example.pruebamoviedb.data.sources.remote.DTO.ListMoviesDTO
import com.example.pruebamoviedb.data.sources.remote.MoviesAPIService
import com.google.gson.Gson
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(private val moviesAPI: MoviesAPIService) {

    private val gson = Gson()

    suspend fun getMoviesLimit(category: String): ListMoviesDTO {
        return moviesAPI.getPopularMovies(category, 1)
    }
}