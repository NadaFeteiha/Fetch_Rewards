package com.nadafeteiha.fetchrewards.ui.home

import com.nadafeteiha.fetchrewards.data.entity.Reward


data class HomeUiState(
    val rewardGroup: Map<Int, List<RewardUiState>> = emptyMap(),
    val rewardGroups: List<Int> = emptyList(),
    val isSortAccessing: Boolean = true,

    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val message: String = ""
)

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