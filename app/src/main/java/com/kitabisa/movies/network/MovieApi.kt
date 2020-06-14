package com.kitabisa.movies.network

import com.kitabisa.movies.model.MovieDetail
import com.kitabisa.movies.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Aryandi Putra<aryandi2712@gmail.com> on 13/06/20.
 */
interface MovieApi {

    @GET("discover/movie")
    suspend fun getMovies(): Response<MovieResponse>

    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<MovieResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): Response<MovieResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): Response<MovieResponse>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): Response<MovieResponse>

    @GET("movie/{id}?append_to_response=reviews")
    suspend fun getMovieDetail(@Path("id") movieId: Int): Response<MovieDetail>

}