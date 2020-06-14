package com.kitabisa.movies.di

import com.kitabisa.movies.repository.MovieRepository
import com.kitabisa.movies.repository.MovieRepositoryImpl
import org.koin.dsl.module

/**
 * @author Aryandi Putra (aryandi.putra@dana.id)
 * @version RepositoryModules, v 0.1 2019-10-21 22:42 by Aryandi Putra
 */
val repositoryModules = module {
    factory<MovieRepository> { MovieRepositoryImpl(get(), get()) }
}