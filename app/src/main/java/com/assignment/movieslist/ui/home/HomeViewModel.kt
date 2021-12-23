package com.assignment.movieslist.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.movieslist.domain.models.Movie
import com.assignment.movieslist.repositories.MovieListRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _movieList = arrayListOf<Movie>()
    val movieList: List<Movie> = _movieList

    private var page = 1
    private var job: Job? = null

    val listUpdateEvent = MutableLiveData<IntRange>()

    init {
        loadMovies()
    }

    fun loadMovies() {
        if (job?.isActive == true) return
        else {
            job = viewModelScope.launch {
                val movies = MovieListRepository.getMoviesList(page++) ?: return@launch

                val startIndex = movieList.size
                _movieList.addAll(movies)
                val endIndex = movieList.size

                listUpdateEvent.postValue(startIndex..endIndex)
            }
        }
    }
}



