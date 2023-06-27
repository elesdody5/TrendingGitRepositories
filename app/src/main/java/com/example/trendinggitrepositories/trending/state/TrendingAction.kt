package com.example.trendinggitrepositories.trending.state

sealed interface TrendingAction {
    object GetTrendingRepos : TrendingAction
}