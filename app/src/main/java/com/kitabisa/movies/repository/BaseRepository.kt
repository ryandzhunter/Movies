package com.kitabisa.movies.repository

import android.util.Log
import com.kitabisa.movies.network.ErrorException
import com.kitabisa.movies.network.Result
import retrofit2.Response
import java.net.SocketTimeoutException
import okhttp3.ResponseBody
import org.json.JSONObject

/**
 * Created by Aryandi Putra<aryandi2712@gmail.com> on 14/06/20.
 */
open class BaseRepository{

    companion object {
        private const val TAG = "BaseRemoteRepository"
        private const val MESSAGE_KEY = "message"
        private const val ERROR_KEY = "error"
    }

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {

        val result : Result<T> = safeApiResult(call,errorMessage)
        var data : T? = null

        when(result) {
            is Result.Success ->
                data = result.data
            is Result.Error -> {
                Log.d("DataRepository", "$errorMessage & Exception - ${result.error}")
            }
        }

        return data
    }

    suspend fun <T: Any> safeApiResult(call: suspend ()-> Response<T>, errorMessage: String) : Result<T>{
        try {
            val response = call.invoke()
            if(response.isSuccessful) return Result.Success(response.body()!!)
            else{
                return Result.Error(ErrorException(response.code(), getErrorMessage(response.errorBody())))
            }
        } catch (se3: SocketTimeoutException) {
            return Result.Error(ErrorException(500, "Socket Timeout"))
        }catch (e: Throwable) {
            return Result.Error(ErrorException(500, "Error, no internet conection "))
        }
    }

    fun getErrorMessage(responseBody: ResponseBody?): String {
        return try {
            val jsonObject = JSONObject(responseBody!!.string())
            when {
                jsonObject.has(MESSAGE_KEY) -> jsonObject.getString(MESSAGE_KEY)
                jsonObject.has(ERROR_KEY) -> jsonObject.getString(ERROR_KEY)
                else -> "Something wrong happened"
            }
        } catch (e: Exception) {
            "Something wrong happened"
        }
    }
}