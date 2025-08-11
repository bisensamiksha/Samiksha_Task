package com.example.samiksha_task.data.models.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val api: HoldingsApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://35dee773a9ec441e9f38d5fc249406ce.api.mockbin.io/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HoldingsApiService::class.java)
    }
}