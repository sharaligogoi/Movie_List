package com.assignment.movieslist.moviedetailsactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.movieslist.domain.models.GenresModel
import com.assignment.movieslist.repositories.MovieListRepository
import kotlinx.coroutines.launch

class MovieDetailsActivityViewModel: ViewModel() {

    private val _genres = MutableLiveData<Map<Int,GenresModel>>()
    val genres: LiveData<Map<Int,GenresModel>>
        get() = _genres

    init {
        viewModelScope.launch {

            _genres.postValue(MovieListRepository.getGenresModelMap())
        }
    }


}