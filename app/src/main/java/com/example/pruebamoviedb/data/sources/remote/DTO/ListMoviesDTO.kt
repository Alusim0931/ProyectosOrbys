package com.example.pruebamoviedb.data.sources.remote.DTO

data class ListMoviesDTO(
    val page: Int,
    val results: List<MoviesResultDTO>,
    val total_pages: Int,
    val total_results: Int
)
