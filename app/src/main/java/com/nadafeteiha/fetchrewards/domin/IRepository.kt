package com.nadafeteiha.fetchrewards.domin

import com.nadafeteiha.fetchrewards.data.entity.Reward

interface IRepository {

    suspend fun getRewards() : Map<Int, List<Reward>>
}