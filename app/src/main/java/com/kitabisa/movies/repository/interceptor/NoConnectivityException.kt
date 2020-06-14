package com.kitabisa.movies.repository.interceptor

import java.io.IOException

/**
 * Created by Aryandi Putra<aryandi2712@gmail.com> on 15/06/20.
 */
class NoConnectivityException : IOException() {
    override val message: String?
        get() = "No Connection"
}