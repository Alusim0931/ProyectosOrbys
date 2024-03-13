package com.example.pruebamoviedb.data.repositories

import com.example.pruebamoviedb.data.sources.remote.MoviesRemoteDataSource
import com.example.pruebamoviedb.domain.models.MoviesList
import com.example.pruebamoviedb.domain.repositories.IMovieListRepository
import com.example.pruebamoviedb.mappers.MoviesDataMapper
import javax.inject.Inject

class MoviesListRepositoryImpl @Inject constructor(private val moviesRemoteDataSource: MoviesRemoteDataSource): IMovieListRepository {

    override suspend fun getMovieList(limit: Int): MoviesList {
        val moviesList = moviesRemoteDataSource.getMoviesLimit(limit)
        return MoviesDataMapper.convertDTOfromList(moviesList)
    }
}

