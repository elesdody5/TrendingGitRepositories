package com.example.trendinggitrepositories.data.remote

import com.example.trendinggitrepositories.data.remote.network.RemoteRepositoryApiService
import com.example.trendinggitrepositories.data.remote.response.RemoteRepository
import java.io.IOException
import javax.inject.Inject

class RepositoriesRemoteDataSource @Inject constructor(
    private val repositoryApiService: RemoteRepositoryApiService
) : RemoteDataSource {
    override suspend fun getRepositories(): Result<List<RemoteRepository>> {
        return try {
            val repositories = repositoryApiService.getRepositories()

            return if (repositories.items != null) {
                Result.success(repositories.items)
            } else {
                Result.failure(Exception("No items found"))
            }
        } catch (e: IOException) {
            Result.failure(e)
        }
    }

}
