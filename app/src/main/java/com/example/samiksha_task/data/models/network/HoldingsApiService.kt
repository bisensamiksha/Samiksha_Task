package com.example.samiksha_task.data.models.network

import ApiResponse
import retrofit2.http.GET

interface HoldingsApiService {
    @GET("/")
    suspend fun getHoldings(): ApiResponse
}