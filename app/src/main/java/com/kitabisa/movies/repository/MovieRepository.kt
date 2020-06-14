package com.kitabisa.movies.repository

import com.kitabisa.movies.model.MovieData

/**
 * Created by Aryandi Putra<aryandi2712@gmail.com> on 13/06/20.
 */
interface MovieRepository {
    suspend fun getMovies(): List<MovieData>?
    suspend fun getPopularMovies(): List<MovieData>?
    suspend fun getUpcomingMovies(): List<MovieData>?
    suspend fun getTopRatedMovies(): List<MovieData>?
    suspend fun getNowPlayingMovies(): List<MovieData>?
}