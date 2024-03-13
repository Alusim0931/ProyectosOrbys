package com.example.pruebamoviedb.data.sources.local

import android.app.Application
import com.example.pruebamoviedb.data.sources.remote.DTO.MoviesResultDTO
import com.google.gson.Gson
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject

class LocalDataMoviesSource @Inject constructor(val application: Application)
{
    fun getMoviesJson(filename: String): MoviesResultDTO {
        val moviesJson = accesJsonAccess(filename)
        val movies = JSONtoModel(moviesJson)

        return movies
    }

    fun accesJsonAccess(json: String): InputStream {
        //Acceso al Json desde assets
        return application.assets.open(json)
    }

    fun JSONtoModel(jsonObject: InputStream): MoviesResultDTO {
        val gson = Gson()
        val reader = InputStreamReader(jsonObject)
        val result: MoviesResultDTO = gson.fromJson(reader, MoviesResultDTO::class.java)
        reader.close()

        return result
    }


}