package com.nadafeteiha.fetchrewards.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nadafeteiha.fetchrewards.ui.composable.AppBar
import com.nadafeteiha.fetchrewards.ui.composable.ErrorView
import com.nadafeteiha.fetchrewards.ui.composable.Loading
import com.nadafeteiha.fetchrewards.ui.theme.RewardTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(onNavigateForward: () -> Unit, viewModel: HomeViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val expandedGroups = remember { mutableStateMapOf<Int, Boolean>() }
    val listener = viewModel

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .background(RewardTheme.colors.background)
    ) {
        AppBar(onSortClick = { listener.onSortClick() })

        if (state.isError) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                ErrorView(onClickRetry = { listener.getData() })
            }
        }
        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                Loading()
            }
        } else {
            if (state.rewardGroup.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.background(RewardTheme.colors.background),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    items(state.rewardGroups) {
                        RewardComponent(
                            groupName = it.toString(),
                            isExpanded = expandedGroups[it] == true,
                            onClick = { expandedGroups[it] = expandedGroups[it] != true },
                            rewards = if (expandedGroups[it] == true) state.rewardGroup[it]
                                ?: emptyList() else emptyList()
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RewardComponent(
    groupName: String,
    isExpanded: Boolean,
    onClick: () -> Unit,
    rewards: List<RewardUiState>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(RewardTheme.colors.secondary)
                .clickable(onClick = onClick)
                .padding(vertical = 16.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "Group ID: $groupName",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.ExtraBold,
                color = RewardTheme.colors.onPrimary
            )

            Icon(
                imageVector = if (isExpanded) {
                    Icons.Default.KeyboardArrowUp
                } else {
                    Icons.Default.KeyboardArrowDown
                },
                contentDescription = null,
                tint = RewardTheme.colors.onPrimary
            )
        }


        rewards.forEach { reward ->
            Text(
                text = reward.name,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                color = RewardTheme.colors.primary
            )
            HorizontalDivider(color = Color.Black, thickness = 1.dp)
        }
    }
}
