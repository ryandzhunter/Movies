package com.kitabisa.movies.di

import android.content.Context
import com.kitabisa.movies.network.MovieApi
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Aryandi Putra<aryandi2712@gmail.com> on 13/06/20.
 */
private const val BASE_URL = "https://api.themoviedb.org/3/"
const val API_KEY = "13495104ae1afe31dcccad8209cee3d0"

val networkModules = module {
    single { provideHttpLoggingInterceptor() }
    single { provideInterceptor(androidContext(), get()) }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { provideMovieApi(get()) }
    single { GsonConverterFactory.create() as Converter.Factory }
}

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.apply {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    }
    return httpLoggingInterceptor
}

fun provideInterceptor(
    context: Context,
    httpLoggingInterceptor: HttpLoggingInterceptor
): ArrayList<Interceptor> {

    val interceptors = arrayListOf<Interceptor>()
    interceptors.add(ChuckInterceptor(context))
    interceptors.add(httpLoggingInterceptor)

    val keyInterceptor = Interceptor { chain ->

        val original = chain.request()
        val originalHttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .build()

        val requestBuilder = original.newBuilder()
            .url(url)

        val request = requestBuilder.build()
        return@Interceptor chain.proceed(request)
    }
    interceptors.add(keyInterceptor)

    return interceptors
}

fun provideOkHttpClient(interceptors: ArrayList<Interceptor>): OkHttpClient {
    val okHttpClient = OkHttpClient().newBuilder()
    if (interceptors.isNotEmpty()) {
        interceptors.forEach {
            okHttpClient.addInterceptor(it)
        }
    }
    return okHttpClient.build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideMovieApi(retrofit: Retrofit): MovieApi {
    return retrofit.create(MovieApi::class.java)
}