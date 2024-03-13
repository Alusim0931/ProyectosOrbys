package com.example.pruebamoviedb.domain.repositories

import com.example.pruebamoviedb.domain.models.Movies
import com.example.pruebamoviedb.domain.models.MoviesList

interface IMovieListRepository {
    suspend fun getMovieList(limit: Int): MoviesList
}