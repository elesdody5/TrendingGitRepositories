package com.example.trending

import com.example.CoroutineTestRule
import com.example.trendinggitrepositories.data.RepositoryGateway
import com.example.trendinggitrepositories.domin.model.GitRepositoryDetails
import com.example.trendinggitrepositories.trending.TrendingViewModel
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.delay
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class TrendingViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @MockK
    private lateinit var repositoryGateway: RepositoryGateway

    private lateinit var trendingViewModel: TrendingViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun getTrendingRepositories_gatewayReturnsSuccessfulList_updateStateToLoadingThenEmitListOfTrendingRepositories() {

        //Given list of trending repositories
        val repos = listOf(GitRepositoryDetails())
        coEvery { repositoryGateway.getRepositories() } coAnswers {
            delay(1000)
            Result.success(repos)
        }
        //When init viewModel
        trendingViewModel = TrendingViewModel(repositoryGateway)

        //Then update state with loading then expected list
        assertThat(trendingViewModel.trendingState.isLoading, `is`(true))
        coroutineTestRule.testDispatcher.scheduler.advanceUntilIdle()

        assertThat(trendingViewModel.trendingState.isLoading, `is`(false))
        assertThat(trendingViewModel.trendingState.repos, `is`(repos))
    }

    @Test
    fun getTrendingRepositories_gatewayReturnsFailure_updateStateToLoadingThenEmitError() {

        //Given failure
        coEvery { repositoryGateway.getRepositories() } coAnswers {
            delay(1000)
            Result.failure(IOException("Network error"))
        }
        //When init viewModel
        trendingViewModel = TrendingViewModel(repositoryGateway)

        //Then update state with loading then expected list
        assertThat(trendingViewModel.trendingState.isLoading, `is`(true))
        coroutineTestRule.testDispatcher.scheduler.advanceUntilIdle()

        assertThat(trendingViewModel.trendingState.isLoading, `is`(false))
        assertThat(trendingViewModel.trendingState.isError, `is`(true))
    }
}