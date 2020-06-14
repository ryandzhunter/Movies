package com.kitabisa.movies.database.dao

import androidx.room.*
import com.kitabisa.movies.model.Movie
import com.kitabisa.movies.model.MovieDetail

/**
 * Created by Aryandi Putra<aryandi2712@gmail.com> on 15/06/20.
 */
@Dao
interface FavoriteMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(favoriteMovieData: Movie) : Long

    @Delete
    suspend fun removeMovie(favoriteMovieData: Movie) : Int

    @Query("SELECT * FROM favorite_movies")
    suspend fun getFavoriteMovies(): List<Movie>

    @Query("SELECT * FROM favorite_movies WHERE id=:movieId")
    suspend fun getMovie(movieId: Int?): Movie?
}