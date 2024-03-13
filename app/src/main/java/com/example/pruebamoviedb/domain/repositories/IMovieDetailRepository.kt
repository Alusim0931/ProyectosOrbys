package com.example.pruebamoviedb.domain.repositories

import com.example.pruebamoviedb.domain.models.Movies
import com.example.pruebamoviedb.domain.models.MoviesList

interface IMovieDetailRepository {
    suspend fun getMoviesData(title: String): Movies
}