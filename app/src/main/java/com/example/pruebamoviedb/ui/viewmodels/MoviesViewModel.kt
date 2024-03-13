package com.example.pruebamoviedb.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebamoviedb.domain.models.Movies
import com.example.pruebamoviedb.domain.usercases.GetMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val useCase: GetMovieDetailUseCase): ViewModel() {

    private var _moviesDTO = MutableLiveData<Movies?>()

    val moviesDTO: MutableLiveData<Movies?> = _moviesDTO

    fun initialized(title: String) {
        viewModelScope.launch {
            _moviesDTO.postValue(withContext(Dispatchers.IO) {
                useCase.getMoviesDetail(title)
            })
        }
    }

}