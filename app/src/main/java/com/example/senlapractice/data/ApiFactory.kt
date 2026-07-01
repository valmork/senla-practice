package com.example.senlapractice.data

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiFactory {

    private val BASE_URL = "https://api.themoviedb.org/3/"
    private val API_KEY = "caa81e29153a8d5f0f550d844f566b39"

    private val apiKeyInterceptor = Interceptor { chain ->
        val original = chain.request()
        val urlWithKey = original.url.newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .build()
        val requestWithKey = original.newBuilder()
            .url(urlWithKey)
            .build()
        chain.proceed(requestWithKey)
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(apiKeyInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val movieApi: MovieApi = retrofit.create(MovieApi::class.java)
}