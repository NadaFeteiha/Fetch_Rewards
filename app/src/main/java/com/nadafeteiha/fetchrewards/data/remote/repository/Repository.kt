package com.nadafeteiha.fetchrewards.data.remote.repository

import com.nadafeteiha.fetchrewards.data.entity.Reward
import com.nadafeteiha.fetchrewards.data.remote.response.toRewardEntity
import com.nadafeteiha.fetchrewards.data.remote.service.RewardsService
import com.nadafeteiha.fetchrewards.domin.IRepository

class Repository(private val service: RewardsService) : IRepository {

    override suspend fun getRewards(): Map<Int, List<Reward>> {
        val result = service.getRewards()
            .filterNot { it.name.isNullOrBlank() }
            .groupBy { it.listId }

        return result.map { (key, value) ->
            key to value.sortedBy { it.name }.map { it.toRewardEntity() }
        }.toMap() as Map<Int, List<Reward>>
    }

}