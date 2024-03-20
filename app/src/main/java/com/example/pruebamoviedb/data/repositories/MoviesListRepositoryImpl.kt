package com.example.pruebamoviedb.data.repositories

import android.graphics.Movie
import com.example.pruebamoviedb.data.sources.local.MovieDatabase
import com.example.pruebamoviedb.data.sources.remote.DTO.MoviesResultDTO
import com.example.pruebamoviedb.data.sources.remote.MoviesAPIService
import com.example.pruebamoviedb.domain.models.MoviesResult
import com.example.pruebamoviedb.domain.repositories.MovieListRepository
import com.example.pruebamoviedb.mappers.toMovie
import com.example.pruebamoviedb.mappers.toMovieEntity
import com.example.pruebamoviedb.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieListRepositoryImpl @Inject constructor(
    private val movieApi: MoviesAPIService,
    private val movieDatabase: MovieDatabase
) : MovieListRepository {

    override suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ): Flow<Resource<List<MoviesResult>>> {
        return flow {

            emit(Resource.Loading(true))

            val localMovieList = movieDatabase.movieDao.getMovieListByCategory(category)

            val shouldLoadLocalMovie = localMovieList.isNotEmpty() && !forceFetchFromRemote

            if (shouldLoadLocalMovie) {
                emit(Resource.Success(
                    data = localMovieList.map { movieEntity ->
                        movieEntity.toMovie(category)
                    }
                ))

                emit(Resource.Loading(false))
                return@flow
            }

            val movieListFromApi = try {
                movieApi.getPopularMovies(category, page, language = "es-ES")
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            }

            val movieEntities = movieListFromApi.results.let {
                it.map { movieDto ->
                    movieDto.toMovieEntity(category)
                }
            }

            movieDatabase.movieDao.upsertMovieList(movieEntities)

            emit(Resource.Success(
                movieEntities.map { it.toMovie(category) }
            ))
            emit(Resource.Loading(false))

        }
    }

    override suspend fun getMovie(id: Int): Flow<Resource<MoviesResult>> {
        return flow {

            emit(Resource.Loading(true))

            val movieEntity = movieDatabase.movieDao.getMovieById(id)

            if (movieEntity != null) {
                emit(
                    Resource.Success(movieEntity.toMovie(movieEntity.category))
                )

                emit(Resource.Loading(false))
                return@flow
            }

            emit(Resource.Error("Error no such movie"))

            emit(Resource.Loading(false))


        }
    }
}

