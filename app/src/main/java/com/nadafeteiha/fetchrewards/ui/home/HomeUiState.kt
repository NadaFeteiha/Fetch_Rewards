package com.nadafeteiha.fetchrewards.ui.home

import com.nadafeteiha.fetchrewards.data.entity.Reward

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val rewardGroup: Map<Int, List<RewardUiState>>, val rewardGroups: List<Int>) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}

data class RewardUiState(
    val id: Int = 0,
    val listId: Int = 0,
    val name: String = ""
)

//region Mappers
fun Map<Int, List<Reward>>.toRewardGroup(): Map<Int, List<RewardUiState>> =
    map { (key, value) ->
        key to value.map { reward -> reward.toRewardUiState() }.sortedBy { it.id }
    }.toMap()

fun Reward.toRewardUiState() = RewardUiState(id = id, name = name)
//endregion