package com.example.pruebamoviedb.domain.models

 data class Movies (
     val title: String,
     val releaseDate: String,
     val posterPath: String,
     val overview: String,
     val voteAverage: Double,
 )