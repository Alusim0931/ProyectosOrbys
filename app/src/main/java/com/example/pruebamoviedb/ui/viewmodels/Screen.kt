package com.example.pruebamoviedb.ui.viewmodels

object Screen {
    const val MovieList = "movie_list"
    const val MovieInfo = "movie_info/{movieId}"

    fun MovieInfo(movieId: String) = "movie_info/$movieId"
}