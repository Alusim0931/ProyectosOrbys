package com.example.pruebamoviedb.data.sources.remote

import com.example.pruebamoviedb.data.sources.remote.DTO.ListMoviesDTO

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesAPIService {
    @GET("movie/{category}")
    suspend fun getPopularMovies(
        @Path("category") category: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "es-ES"
    ): ListMoviesDTO

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
        const val API_KEY = "1ebadc1119eb4ffcedb9451c863b9687"
    }

}