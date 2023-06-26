package com.example.trendinggitrepositories.data

import com.example.trendinggitrepositories.domin.model.GitRepositoryDetails

interface RepositoryGateway {
    suspend fun getRepositories(): Result<List<GitRepositoryDetails>>
}