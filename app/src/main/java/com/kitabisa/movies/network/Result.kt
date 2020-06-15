package com.kitabisa.movies.network

/**
 * Created by Aryandi Putra<aryandi2712@gmail.com> on 14/06/20.
 */
sealed class Result<out T: Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val error: ErrorException?) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

data class ErrorException(val code: Int, val message : String)