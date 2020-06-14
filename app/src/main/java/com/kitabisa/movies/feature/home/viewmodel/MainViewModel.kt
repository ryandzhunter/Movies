package com.kitabisa.movies.feature.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kitabisa.movies.model.Movie
import com.kitabisa.movies.repository.MovieRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent

/**
 * Created by Aryandi Putra<aryandi2712@gmail.com> on 14/06/20.
 */
class MainViewModel(private val movieRepository: MovieRepository) : ViewModel(), KoinComponent {

    val moviesLiveData = MutableLiveData<List<Movie>>()

    val handler = CoroutineExceptionHandler { _, exception ->
        //Handle your exception
    }

    fun getMovies(){
        viewModelScope.launch(handler) {
            val movies = withContext(Dispatchers.IO) {
                movieRepository.getMovies()
            }
            moviesLiveData.value = movies
        }
    }

    fun getPopularMovies(){
        viewModelScope.launch(handler) {
            val movies = withContext(Dispatchers.IO) {
                movieRepository.getPopularMovies()
            }
            moviesLiveData.value = movies
        }
    }

    fun getUpcomingMovies(){
        viewModelScope.launch(handler) {
            val movies = withContext(Dispatchers.IO) {
                movieRepository.getUpcomingMovies()
            }
            moviesLiveData.value = movies
        }
    }

    fun getTopRatedMovies(){
        viewModelScope.launch(handler) {
            val movies = withContext(Dispatchers.IO) {
                movieRepository.getTopRatedMovies()
            }
            moviesLiveData.value = movies
        }
    }

    fun getNowPlayingMovies(){
        viewModelScope.launch(handler) {
            val movies = withContext(Dispatchers.IO) {
                movieRepository.getNowPlayingMovies()
            }
            moviesLiveData.value = movies
        }
    }

    fun getFavoriteMovies(){
        viewModelScope.launch(handler) {
            val movies = withContext(Dispatchers.IO) {
                movieRepository.getAllFavoriteMovies()
            }
            moviesLiveData.value = movies
        }
    }
}