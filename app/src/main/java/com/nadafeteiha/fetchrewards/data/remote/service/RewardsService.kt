package com.nadafeteiha.fetchrewards.data.remote.service

import com.nadafeteiha.fetchrewards.data.remote.response.RewardsDTO
import retrofit2.http.GET

interface RewardsService {
    @GET("hiring.json")
    suspend fun getRewards(): List<RewardsDTO>
}