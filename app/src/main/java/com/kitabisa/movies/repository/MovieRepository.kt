package com.kitabisa.movies.repository

import com.kitabisa.movies.model.MovieData

/**
 * Created by Aryandi Putra<aryandi2712@gmail.com> on 13/06/20.
 */
interface MovieRepository {
    suspend fun getMovies(): List<MovieData>?
}