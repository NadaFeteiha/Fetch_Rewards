package com.nadafeteiha.fetchrewards.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadafeteiha.fetchrewards.data.remote.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class HomeViewModel(private val repository: Repository) : ViewModel(), KoinComponent {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        fetchRewards()
    }

    internal fun fetchRewards() {
        viewModelScope.launch {
            try {
                val rewards = repository.getRewards().toRewardGroup().toSortedMap()
                _uiState.value = HomeUiState.Success(rewards, rewards.keys.toList())
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error(e.message ?: "Unknown Error")
            }
        }
    }

    fun onSortClick() {
        _uiState.update { currentState ->
            if (currentState is HomeUiState.Success) {
                currentState.copy(
                    rewardGroups = currentState.rewardGroups.reversed()
                )
            } else {
                currentState
            }
        }
    }
}