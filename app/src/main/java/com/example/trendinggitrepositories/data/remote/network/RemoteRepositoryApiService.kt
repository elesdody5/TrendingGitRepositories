package com.example.trendinggitrepositories.data.remote.network

import com.example.trendinggitrepositories.data.remote.response.RepositoryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteRepositoryApiService {
    @GET(REPOSITORIES_URL)
    suspend fun getRepositories(
        @Query("q") query: String = "language",
        @Query("sort") sort: String = "stars"
    ): RepositoryResponse
}