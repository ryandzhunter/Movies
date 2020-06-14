package com.kitabisa.movies.di

import com.kitabisa.movies.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Aryandi Putra<aryandi2712@gmail.com> on 14/06/20.
 */
val viewModelModules = module() {
    viewModel{ MainViewModel(get()) }
}