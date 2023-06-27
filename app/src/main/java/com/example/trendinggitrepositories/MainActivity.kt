package com.example.trendinggitrepositories

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.trendinggitrepositories.trending.TrendingScreen
import com.example.trendinggitrepositories.trending.TrendingViewModel
import com.example.trendinggitrepositories.ui.theme.TrendingGitRepositoriesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel by viewModels<TrendingViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isDarkMode by remember { mutableStateOf<Boolean?>(null) }
            TrendingGitRepositoriesTheme(
                darkTheme = isDarkMode ?: isSystemInDarkTheme()
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TrendingScreen(
                        trendingState = viewModel.trendingState,
                        isDarkMode = isDarkMode ?: isSystemInDarkTheme(),
                        event = viewModel::reducer
                    ) { isDarkMode = it }
                }
            }
        }
    }
}
