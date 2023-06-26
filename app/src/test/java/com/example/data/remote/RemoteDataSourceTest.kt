package com.example.data.remote

import com.example.trendinggitrepositories.data.remote.RemoteDataSource
import com.example.trendinggitrepositories.data.remote.RepositoriesRemoteDataSource
import com.example.trendinggitrepositories.data.remote.network.RemoteRepositoryApiService
import com.example.trendinggitrepositories.data.remote.response.RemoteRepository
import com.example.trendinggitrepositories.data.remote.response.RepositoryResponse
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class RemoteDataSourceTest {

    @MockK
    private lateinit var apiService: RemoteRepositoryApiService

    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        remoteDataSource = RepositoriesRemoteDataSource(apiService)

    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun getRemoteRepositories_apiCallSuccessful_successfulResultWithRepositoriesList() = runTest {
        // Given the api return successful response with list of repositories
        val repositories = listOf(RemoteRepository(name = "Test repository"))
        val response = RepositoryResponse(items = repositories)
        coEvery { apiService.getRepositories() } returns response

        //When get remote repositories.
        val result = remoteDataSource.getRepositories()

        //Then the response should be success and hold the repos list
        assertThat(result.isSuccess, `is`(true))
        assertThat(result.getOrNull(), `is`(repositories))
    }

    @Test
    fun getRemoteRepositories_apiReturnsNull_returnsFailure() = runTest {
        // Given the api return null items
        val response = RepositoryResponse(items = null)
        coEvery { apiService.getRepositories() } returns response

        //When get remote repositories.
        val result = remoteDataSource.getRepositories()

        //Then the response should be failure
        assertThat(result.isFailure, `is`(true))
    }

    @Test
    fun getRemoteRepositories_throwsIOException_returnsFailure() = runTest {
        // Given the api return throws exception
        coEvery { apiService.getRepositories() } throws IOException("Network error")

        //When get remote repositories.
        val result = remoteDataSource.getRepositories()

        //Then the response should be success and hold the repos list
        assertThat(result.isFailure, `is`(true))
    }

}