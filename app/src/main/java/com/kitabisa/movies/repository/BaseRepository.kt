package com.kitabisa.movies.repository

import android.util.Log
import com.kitabisa.movies.network.Result
import retrofit2.Response
import java.io.IOException

/**
 * Created by Aryandi Putra<aryandi2712@gmail.com> on 14/06/20.
 */
open class BaseRepository{

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {

        val result : Result<T> = safeApiResult(call,errorMessage)
        var data : T? = null

        when(result) {
            is Result.Success ->
                data = result.data
            is Result.Error -> {
                Log.d("DataRepository", "$errorMessage & Exception - ${result.exception}")
            }
        }

        return data
    }

    private suspend fun <T: Any> safeApiResult(call: suspend ()-> Response<T>, errorMessage: String) : Result<T>{

        try {
            val response = call.invoke()
            if(response.isSuccessful) return Result.Success(response.body()!!)
        } catch (e: Exception) {
            return Result.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
        }

        return Result.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
    }
}