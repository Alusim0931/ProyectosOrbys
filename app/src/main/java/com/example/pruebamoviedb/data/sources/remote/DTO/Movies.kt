package com.example.pruebamoviedb.data.sources.remote.DTO

data class Movies(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)