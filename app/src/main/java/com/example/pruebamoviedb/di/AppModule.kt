package com.example.pruebamoviedb.di

import com.example.pruebamoviedb.data.repositories.MoviesDetailRepositoryImpl
import com.example.pruebamoviedb.data.sources.local.LocalDataMoviesSource
import com.example.pruebamoviedb.data.sources.remote.MoviesAPIService
import com.example.pruebamoviedb.data.sources.remote.MoviesRemoteDataSource
import com.example.pruebamoviedb.domain.repositories.IMovieDetailRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/popular?api_key=1ebadc1119eb4ffcedb9451c863b9687")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMoviesService(retrofit: Retrofit): MoviesAPIService {
        return retrofit.create(MoviesAPIService::class.java)
    }

    @Provides
    @Singleton
    fun provideMoviesDetailRepository(moviesLocalDataSource: LocalDataMoviesSource,
                                      moviesRemoteDataSource: MoviesRemoteDataSource): IMovieDetailRepository {
        return MoviesDetailRepositoryImpl(moviesLocalDataSource, moviesRemoteDataSource)
    }



}