package com.kitabisa.movies.repository

import com.kitabisa.movies.database.dao.FavoriteMovieDao
import com.kitabisa.movies.network.MovieApi
import com.kitabisa.movies.model.Movie
import com.kitabisa.movies.model.MovieDetail
import com.kitabisa.movies.model.MovieResponse
import retrofit2.Response

class MovieRepositoryImpl(private val movieApi: MovieApi, private val favoriteMovieDao: FavoriteMovieDao) : BaseRepository(), MovieRepository {

    override suspend fun getMovies(): List<Movie>? {
        return fetchAPI(movieApi.getMovies(), "Error Fetching Movies")
    }

    override suspend fun getPopularMovies(): List<Movie>? {
        return fetchAPI(movieApi.getPopularMovies(), "Error Fetching Popular Movies")
    }

    override suspend fun getUpcomingMovies(): List<Movie>? {
        return fetchAPI(movieApi.getUpcomingMovies(), "Error Fetching Upcoming Movies")
    }

    override suspend fun getTopRatedMovies(): List<Movie>? {
        return fetchAPI(movieApi.getTopRatedMovies(), "Error Fetching Top Rated Movies")
    }

    override suspend fun getNowPlayingMovies(): List<Movie>? {
        return fetchAPI(movieApi.getNowPlayingMovies(), "Error Fetching Now Playing Movies")
    }

    override suspend fun insertFavoriteMovie(movie: Movie): Long {
        return favoriteMovieDao.insertMovie(movie)
    }

    override suspend fun removeFavoriteMovie(movie: Movie): Int {
        return favoriteMovieDao.removeMovie(movie)
    }

    override suspend fun getAllFavoriteMovies(): List<Movie> {
        return favoriteMovieDao.getFavoriteMovies()
    }

    override suspend fun isFavoriteMovieExist(movieId: Int): Movie? {
        return favoriteMovieDao.getMovie(movieId)
    }

    override suspend fun getMovieDetail(id: Int): MovieDetail? {
        return safeApiCall(
            call = {movieApi.getMovieDetail(id)},
            errorMessage = "Error Fetching Now Playing Movies"
        )
    }

    private suspend fun fetchAPI(
        movies: Response<MovieResponse>,
        errorMessage: String
    ): List<Movie>? {
        val movieResponse = safeApiCall(
            call = { movies },
            errorMessage = errorMessage
        )
        return movieResponse?.results
    }

}