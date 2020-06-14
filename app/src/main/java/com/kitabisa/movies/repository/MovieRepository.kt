package com.kitabisa.movies.repository

import com.kitabisa.movies.model.Movie
import com.kitabisa.movies.model.MovieDetail

/**
 * Created by Aryandi Putra<aryandi2712@gmail.com> on 13/06/20.
 */
interface MovieRepository {
    suspend fun getMovies(): List<Movie>?
    suspend fun getPopularMovies(): List<Movie>?
    suspend fun getUpcomingMovies(): List<Movie>?
    suspend fun getTopRatedMovies(): List<Movie>?
    suspend fun getNowPlayingMovies(): List<Movie>?
    suspend fun getMovieDetail(id: Int): MovieDetail?
    suspend fun insertFavoriteMovie(movie: Movie) : Long
    suspend fun removeFavoriteMovie(movie: Movie) : Int
    suspend fun getAllFavoriteMovies(): List<Movie>
    suspend fun isFavoriteMovieExist(movieId: Int) : Movie?
}