package com.example.data

import com.example.trendinggitrepositories.data.RepositoryGateway
import com.example.trendinggitrepositories.data.RepositoryGatewayImp
import com.example.trendinggitrepositories.data.remote.RemoteDataSource
import com.example.trendinggitrepositories.data.remote.response.RemoteRepository
import com.example.trendinggitrepositories.data.remote.response.toGitRepositoryDetails
import com.example.trendinggitrepositories.domin.model.GitRepositoryDetails
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
import org.mockito.Mock

@OptIn(ExperimentalCoroutinesApi::class)
class RepositoryGatewayTest {

    @MockK
    private lateinit var remoteDataSource: RemoteDataSource

    private lateinit var repositoryGateWay: RepositoryGateway

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repositoryGateWay = RepositoryGatewayImp(remoteDataSource)
    }
    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun getRepositories_successApiCall_returnSuccessResultWithRepoList() = runTest {
        //Given list of repos response
        val repos = listOf(RemoteRepository(name = "Test"))
        coEvery { remoteDataSource.getRepositories() } returns Result.success(repos)

        //When get repositories
        val result = repositoryGateWay.getRepositories()
        val expected = listOf(GitRepositoryDetails(name = "Test"))
        //Then result is success map to domain object successful
        assertThat(result.isSuccess, `is`(true))
        assertThat(result.getOrNull(), `is`(expected))
    }

}