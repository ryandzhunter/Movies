package com.kitabisa.movies.feature.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kitabisa.movies.model.Movie
import com.kitabisa.movies.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent

/**
 * Created by Aryandi Putra<aryandi2712@gmail.com> on 14/06/20.
 */
class MainViewModel(private val movieRepository: MovieRepository) : ViewModel(), KoinComponent {

    val moviesLiveData = MutableLiveData<List<Movie>>()

    fun getMovies(){
        viewModelScope.launch {
            val movies = withContext(Dispatchers.IO) {
                movieRepository.getMovies()
            }
            moviesLiveData.value = movies
        }
    }

    fun getPopularMovies(){
        viewModelScope.launch {
            val movies = withContext(Dispatchers.IO) {
                movieRepository.getPopularMovies()
            }
            moviesLiveData.value = movies
        }
    }

    fun getUpcomingMovies(){
        viewModelScope.launch {
            val movies = withContext(Dispatchers.IO) {
                movieRepository.getUpcomingMovies()
            }
            moviesLiveData.value = movies
        }
    }

    fun getTopRatedMovies(){
        viewModelScope.launch {
            val movies = withContext(Dispatchers.IO) {
                movieRepository.getTopRatedMovies()
            }
            moviesLiveData.value = movies
        }
    }

    fun getNowPlayingMovies(){
        viewModelScope.launch {
            val movies = withContext(Dispatchers.IO) {
                movieRepository.getNowPlayingMovies()
            }
            moviesLiveData.value = movies
        }
    }

}