package com.kitabisa.movies.repository

import com.kitabisa.movies.network.MovieApi
import com.kitabisa.movies.model.MovieData
import com.kitabisa.movies.model.MovieResponse
import retrofit2.Response

class MovieRepositoryImpl(private val movieApi: MovieApi) : BaseRepository(), MovieRepository {

    override suspend fun getMovies(): List<MovieData>? {
        return fetchAPI(movieApi.getMovies(), "Error Fetching Movies")
    }

    override suspend fun getPopularMovies(): List<MovieData>? {
        return fetchAPI(movieApi.getPopularMovies(), "Error Fetching Popular Movies")
    }

    override suspend fun getUpcomingMovies(): List<MovieData>? {
        return fetchAPI(movieApi.getUpcomingMovies(), "Error Fetching Upcoming Movies")
    }

    override suspend fun getTopRatedMovies(): List<MovieData>? {
        return fetchAPI(movieApi.getTopRatedMovies(), "Error Fetching Top Rated Movies")
    }

    override suspend fun getNowPlayingMovies(): List<MovieData>? {
        return fetchAPI(movieApi.getNowPlayingMovies(), "Error Fetching Now Playing Movies")
    }

    private suspend fun fetchAPI(
        movies: Response<MovieResponse>,
        errorMessage: String
    ): List<MovieData>? {
        val movieResponse = safeApiCall(
            call = { movies },
            errorMessage = errorMessage
        )
        return movieResponse?.results
    }

}