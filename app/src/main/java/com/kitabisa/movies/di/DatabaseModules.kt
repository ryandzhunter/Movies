package com.kitabisa.movies.di

import androidx.room.Room
import com.kitabisa.movies.database.DB_NAME
import com.kitabisa.movies.database.FavoriteMovieDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Aryandi Putra<aryandi2712@gmail.com> on 15/06/20.
 */
val dataBaseModules = module {
    single {
        Room.databaseBuilder(androidContext(), FavoriteMovieDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration().build()
    }
    factory {
        get<FavoriteMovieDatabase>().favoriteMovieDao()
    }
}