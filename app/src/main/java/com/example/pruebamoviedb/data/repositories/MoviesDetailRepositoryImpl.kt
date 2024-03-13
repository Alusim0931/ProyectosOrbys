package com.example.pruebamoviedb.data.repositories

import com.example.pruebamoviedb.data.sources.local.LocalDataMoviesSource
import com.example.pruebamoviedb.data.sources.remote.DTO.ListMoviesDTO
import com.example.pruebamoviedb.data.sources.remote.DTO.MoviesResultDTO
import com.example.pruebamoviedb.data.sources.remote.MoviesRemoteDataSource
import com.example.pruebamoviedb.domain.models.Movies
import com.example.pruebamoviedb.domain.models.MoviesList
import com.example.pruebamoviedb.domain.repositories.IMovieDetailRepository
import com.example.pruebamoviedb.mappers.MoviesDataMapper
import javax.inject.Inject

class MoviesDetailRepositoryImpl @Inject constructor(
    private val movieLocalDataSource: LocalDataMoviesSource,
    private val movieRemoteDataSource: MoviesRemoteDataSource): IMovieDetailRepository {

    override suspend fun getMoviesData(title: String): Movies {
        val moviesDTO: MoviesResultDTO = movieRemoteDataSource.getMoviesTitle(title)
        return MoviesDataMapper.TransformMovieFromDTO(moviesDTO)
    }
}