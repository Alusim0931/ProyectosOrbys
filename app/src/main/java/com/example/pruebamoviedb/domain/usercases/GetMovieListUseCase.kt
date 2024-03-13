package com.example.pruebamoviedb.domain.usercases

import com.example.pruebamoviedb.data.repositories.MoviesListRepositoryImpl
import com.example.pruebamoviedb.domain.models.MoviesList
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(private val movieRepository: MoviesListRepositoryImpl) {
    suspend fun getMovieList(limit: Int): MoviesList {
        return movieRepository.getMovieList(limit)
    }
}