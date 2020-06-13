package com.kitabisa.moviedb.network

import com.kitabisa.movies.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Aryandi Putra<aryandi2712@gmail.com> on 13/06/20.
 */
interface MovieApi {

    @GET("discover/movie")
    suspend fun getMovies(): Response<MovieResponse>

}