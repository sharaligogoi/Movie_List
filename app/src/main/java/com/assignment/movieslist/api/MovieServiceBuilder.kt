package com.assignment.movieslist.api

import com.assignment.movieslist.api.interceptors.QueryParameterInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieServiceBuilder {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    private val okHttp = OkHttpClient.Builder()
        .addInterceptor(
            QueryParameterInterceptor(
                "api_key", "5c74df255b6ea5ebc068963d3ee755aa"
            )
        ).addInterceptor(
            QueryParameterInterceptor(
                "language", "en-US"
            )
        )

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build()).build()

    fun<T> buildMovieServices(serviceType: Class<T>): T{
        return retrofit.create(serviceType)
    }

}