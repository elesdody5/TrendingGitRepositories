package com.example.trendinggitrepositories.trending

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.trendinggitrepositories.R
import com.example.trendinggitrepositories.trending.component.RetryScreen
import com.example.trendinggitrepositories.trending.component.ShimmerList
import com.example.trendinggitrepositories.trending.component.TrendingList
import com.example.trendinggitrepositories.trending.state.TrendingAction
import com.example.trendinggitrepositories.trending.state.TrendingState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrendingScreen(
    trendingState: TrendingState,
    event: (TrendingAction) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = stringResource(id = R.string.trending)) })
        }
    ) { padding ->
        if (trendingState.isLoading) {
            ShimmerList()
        } else if (trendingState.isError) {
            RetryScreen {
                event(TrendingAction.GetTrendingRepos)
            }
        } else {
            TrendingList(
                modifier = Modifier.padding(padding),
                trendingRepos = trendingState.repos
            )
        }
    }
}