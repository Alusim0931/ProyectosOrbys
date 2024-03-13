package com.example.pruebamoviedb.domain.usercases

import com.example.pruebamoviedb.data.repositories.MoviesDetailRepositoryImpl
import com.example.pruebamoviedb.domain.models.Movies
import com.example.pruebamoviedb.domain.models.MoviesList
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(private val movieRepository: MoviesDetailRepositoryImpl) {

    suspend fun getMovieDetail(title: String): Movies {
        return movieRepository.getMoviesData(title)
    }
}