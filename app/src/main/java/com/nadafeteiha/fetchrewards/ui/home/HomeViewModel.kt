package com.nadafeteiha.fetchrewards.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadafeteiha.fetchrewards.data.remote.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class HomeViewModel(private val repository: Repository) : ViewModel(), KoinComponent {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    init {
        getData()
    }

    fun getData() {
        _state.update { it.copy(isLoading = true, isError = false, message = "") }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repository.getRewards().toRewardGroup().toSortedMap()
                println(result)
                _state.update {
                    it.copy(
                        isLoading = false, rewardGroup = result, rewardGroups = result.keys.toList()
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(isLoading = true, isError = true, message = e.message.toString())
                }
            }
        }
    }

    fun onSortClick() {
        _state.update {
            it.copy(
                isSortAccessing = !it.isSortAccessing, rewardGroups = it.rewardGroups.reversed(),
            )
        }
    }
}