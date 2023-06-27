package com.example.trendinggitrepositories.trending.state

import com.example.trendinggitrepositories.domin.model.GitRepositoryDetails

data class TrendingState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val repos: List<GitRepositoryDetails> = emptyList()
)
