package com.example.pruebamoviedb.mappers

import com.example.pruebamoviedb.data.sources.remote.DTO.ListMoviesDTO
import com.example.pruebamoviedb.data.sources.remote.DTO.MoviesResultDTO
import com.example.pruebamoviedb.domain.models.Movies
import com.example.pruebamoviedb.domain.models.MoviesList
import javax.inject.Inject

class MoviesDataMapper @Inject constructor(val moviesResultDTO: MoviesResultDTO) {
    companion object {
        fun TransformMovieFromDTO(moviesResultDTO: MoviesResultDTO): Movies {
            
            val movies = Movies(
                moviesResultDTO.title,
                moviesResultDTO.release_date,
                moviesResultDTO.poster_path,
                moviesResultDTO.overview,
                moviesResultDTO.vote_average,
            )
            return movies
        }

        fun convertDTOfromList(listmovies: ListMoviesDTO): MoviesList{
            val nameListMovies = mutableListOf<String>()

            for (movies in listmovies.results) {
                nameListMovies.add(movies.title)
            }

            return MoviesList(nameListMovies)
        }
    }
}