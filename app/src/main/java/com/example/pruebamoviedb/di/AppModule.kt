package com.example.pruebamoviedb.di

import android.app.Application
import androidx.room.Room
import com.example.pruebamoviedb.data.sources.local.LocalDataMoviesSource
import com.example.pruebamoviedb.data.sources.local.MovieDatabase
import com.example.pruebamoviedb.data.sources.remote.MoviesAPIService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object NetworkModule {
    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(): MoviesAPIService {
        return Retrofit.Builder()
            .baseUrl(MoviesAPIService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(MoviesAPIService::class.java)
    }

    @Provides
    @Singleton
    fun providesMovieDatabase(app: Application): MovieDatabase {
        return Room.databaseBuilder(
            app,
            MovieDatabase::class.java,
            "moviedb.db"
        ).build()
    }

}