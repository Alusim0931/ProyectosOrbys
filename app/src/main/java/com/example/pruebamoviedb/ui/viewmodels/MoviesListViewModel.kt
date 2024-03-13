package com.example.pruebamoviedb.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebamoviedb.domain.models.MoviesList
import com.example.pruebamoviedb.domain.usercases.GetMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val MAX_MOVIES = 1000
@HiltViewModel
class MoviesListViewModel @Inject constructor(private val useCase: GetMovieListUseCase): ViewModel() {
    private var _moviesList = MutableLiveData<MoviesList?>()
    val moviesList: LiveData<MoviesList?> = _moviesList

    init {
        viewModelScope.launch {
            _moviesList.postValue(withContext(Dispatchers.IO) {
                useCase.getMovieList(MAX_MOVIES)
            })
        }
    }
}