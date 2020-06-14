package com.kitabisa.movies

import android.app.Application
import com.kitabisa.movies.di.dataBaseModules
import com.kitabisa.movies.di.networkModules
import com.kitabisa.movies.di.repositoryModules
import com.kitabisa.movies.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Aryandi Putra<aryandi2712@gmail.com> on 13/06/20.
 */
class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MovieApplication)
            modules(
                listOf(
                    dataBaseModules, networkModules, repositoryModules, viewModelModules
                )
            )
        }
    }
}