package com.example.samiksha_task.data.models.network

import UserHolding

class HoldingsRepository(
    private val api: HoldingsApiService
) {
    suspend fun fetchHoldings(): List<UserHolding> {
        return api.getHoldings().data?.userHolding ?: emptyList()
    }
}