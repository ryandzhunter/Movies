package com.kitabisa.movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kitabisa.movies.model.MovieData
import com.kitabisa.movies.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent

/**
 * Created by Aryandi Putra<aryandi2712@gmail.com> on 14/06/20.
 */
class MainViewModel(private val movieRepository: MovieRepository) : ViewModel(), KoinComponent {

    val moviesLiveData = MutableLiveData<List<MovieData>>()

    fun getMovies(){
        viewModelScope.launch {
            val movies = withContext(Dispatchers.IO) {
                movieRepository.getMovies()
            }
            moviesLiveData.value = movies
        }
    }

}