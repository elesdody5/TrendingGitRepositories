package com.example.trendinggitrepositories.data

import com.example.trendinggitrepositories.data.remote.RemoteDataSource
import com.example.trendinggitrepositories.data.remote.response.toGitRepositoryDetails
import com.example.trendinggitrepositories.domin.model.GitRepositoryDetails
import javax.inject.Inject

class RepositoryGatewayImp @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    RepositoryGateway {
    override suspend fun getRepositories(): Result<List<GitRepositoryDetails>> {
        return remoteDataSource.getRepositories().map { it.toGitRepositoryDetails() }
    }
}