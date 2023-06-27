package com.example.trendinggitrepositories.trending

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trendinggitrepositories.data.RepositoryGateway
import com.example.trendinggitrepositories.trending.state.TrendingAction
import com.example.trendinggitrepositories.trending.state.TrendingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(private val repositoryGateway: RepositoryGateway) :
    ViewModel() {

    var trendingState by mutableStateOf(TrendingState())
        private set
    init {
        reducer(TrendingAction.GetTrendingRepos)
    }

    fun reducer(trendingAction: TrendingAction) {
        when (trendingAction) {
            is TrendingAction.GetTrendingRepos -> getTrendingRepos()
        }
    }

    private fun getTrendingRepos() {
        viewModelScope.launch {

            trendingState = TrendingState(isLoading = true)
            val result = repositoryGateway.getRepositories()

            result.onSuccess {
                trendingState = TrendingState(repos = it)
            }.onFailure {
                trendingState = TrendingState(isError = true)
            }
        }
    }
}