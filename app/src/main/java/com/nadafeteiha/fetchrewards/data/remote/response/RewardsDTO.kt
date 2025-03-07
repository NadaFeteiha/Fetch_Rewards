package com.nadafeteiha.fetchrewards.data.remote.response

import com.nadafeteiha.fetchrewards.data.entity.Reward
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RewardsDTO(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("listId")
    val listId: Int? = null,
    @SerialName("name")
    val name: String? = null
)

fun RewardsDTO.toRewardEntity(): Reward {
    return Reward(
        id = id ?: 0,
        listId = listId ?: 0,
        name = name ?: ""
    )
}
