package com.example.pruebamoviedb.data.sources.remote

import com.example.pruebamoviedb.data.sources.remote.DTO.ListMoviesDTO
import com.example.pruebamoviedb.data.sources.remote.DTO.MoviesResultDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesAPIService {
    @GET("movie/popular?api_key=1ebadc1119eb4ffcedb9451c863b9687")
    suspend fun getPopularMovies(@Query("limit") limit: Int): ListMoviesDTO

    @GET("movie/{title}")
    suspend fun getMovies(@Path("title") title: String): MoviesResultDTO


}