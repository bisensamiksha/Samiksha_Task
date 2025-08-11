package com.example.samiksha_task.data.models.network

import com.example.assignmentholdings.models.UserHolding

class HoldingsRepository(
    private val api: HoldingsApiService
) {
    suspend fun fetchHoldings(): List<UserHolding> {
        return api.getHoldings().data?.userHolding ?: emptyList()
    }
}