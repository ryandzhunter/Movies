package com.kitabisa.movies.repository

import com.kitabisa.movies.network.MovieApi
import com.kitabisa.movies.model.MovieData

class MovieRepositoryImpl(private val movieApi: MovieApi) : BaseRepository(), MovieRepository {

    override suspend fun getMovies(): List<MovieData>? {

        val movieResponse = safeApiCall(
            call = {movieApi.getMovies()},
            errorMessage = "Error Fetching Popular Movies"
        )

        return movieResponse?.results
    }

}