package com.example.pruebamoviedb.data.sources.remote

import com.example.pruebamoviedb.data.sources.remote.DTO.ListMoviesDTO
import com.example.pruebamoviedb.data.sources.remote.DTO.MoviesResultDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesAPIService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("limit") limit: Int): ListMoviesDTO

    @GET("movie/{title}")
    suspend fun getMovies(@Path("title") title: String): MoviesResultDTO


}