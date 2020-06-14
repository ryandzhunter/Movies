package com.kitabisa.movies.feature.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kitabisa.movies.model.Movie
import com.kitabisa.movies.model.MovieDetail
import com.kitabisa.movies.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Aryandi Putra<aryandi2712@gmail.com> on 14/06/20.
 */
class MovieDetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val moviesLiveData = MutableLiveData<MovieDetail>()
    var favoriteState: MutableLiveData<Boolean> = MutableLiveData()

    fun getMovieDetail(id:Int){
        viewModelScope.launch {
            val movie = withContext(Dispatchers.IO) {
                movieRepository.getMovieDetail(id)
            }
            moviesLiveData.value = movie
        }
    }

    fun isMovieFavorite(id:Int){
        viewModelScope.launch {
            val movie = withContext(Dispatchers.IO) {
                movieRepository.isFavoriteMovieExist(id)
            }
            favoriteState.value = movie != null
        }
    }

    fun addFavorite(movie: Movie){
        viewModelScope.launch {
            val success = withContext(Dispatchers.IO) {
                movieRepository.insertFavoriteMovie(movie)
            }
            favoriteState.value = success > 0
        }
    }

    fun removeFavorite(movie:Movie){
        viewModelScope.launch {
            val success = withContext(Dispatchers.IO) {
                movieRepository.removeFavoriteMovie(movie)
            }
            favoriteState.value = success > 0
        }
    }

    fun onFavoriteButtonClicked(movie: Movie) {
        if (favoriteState.value == true) {
            removeFavorite(movie)
        } else {
            addFavorite(movie)
        }
    }

}