package com.kitabisa.movies.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kitabisa.movies.database.dao.FavoriteMovieDao
import com.kitabisa.movies.database.typeconverter.GenreConverter
import com.kitabisa.movies.model.Movie

/**
 * Created by Aryandi Putra<aryandi2712@gmail.com> on 15/06/20.
 */
@Database(entities = [Movie::class], version = 1, exportSchema = false)
@TypeConverters(GenreConverter::class)
abstract class FavoriteMovieDatabase : RoomDatabase() {
    abstract fun favoriteMovieDao() : FavoriteMovieDao
}