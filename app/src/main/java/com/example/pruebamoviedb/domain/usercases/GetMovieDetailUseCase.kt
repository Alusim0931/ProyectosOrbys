package com.example.pruebamoviedb.domain.usercases

import com.example.pruebamoviedb.data.repositories.MoviesRemoteDataSource
import com.example.pruebamoviedb.data.sources.remote.DTO.ListMoviesDTO
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val movieRepository: MoviesRemoteDataSource) {

    suspend fun getPopularMovies(category: String): ListMoviesDTO {
        return movieRepository.getMoviesLimit(category)
    }
}