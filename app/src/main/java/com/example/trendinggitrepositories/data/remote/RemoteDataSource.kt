package com.example.trendinggitrepositories.data.remote

import com.example.trendinggitrepositories.data.remote.response.RemoteRepository

interface RemoteDataSource {
    suspend fun getRepositories():Result<List<RemoteRepository>>
}