package com.example.trendinggitrepositories.trending.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.trendinggitrepositories.R
import com.example.trendinggitrepositories.domin.model.GitRepositoryDetails
import com.example.trendinggitrepositories.ui.theme.TrendingGitRepositoriesTheme

@Composable
fun TrendingList(
    modifier: Modifier = Modifier,
    trendingRepos: List<GitRepositoryDetails>
) {
    var selectedIndex by remember { mutableStateOf(0) }

    LazyColumn(Modifier.testTag("TrendingList")) {
        items(trendingRepos.size) { index ->
            TrendingListItem(
                trendingRepos[index],
                selectedIndex == index
            ) { selectedIndex = index }

            Divider(Modifier.padding(vertical = 10.dp))
        }
    }
}

@Composable
fun TrendingListItem(
    trendingRepo: GitRepositoryDetails,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    val uriHandler = LocalUriHandler.current

    Row(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .testTag("TrendingListItem")
            .clickable(onClick = onSelect),
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(trendingRepo.avatar)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(R.string.description),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .clip(CircleShape)
                .size(80.dp)
        )

        Column(
            Modifier.padding(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(text = trendingRepo.name)
            Text(text = trendingRepo.description)
            AnimatedVisibility(visible = isSelected) {
                Column {
                    Text(
                        text = trendingRepo.url,
                        Modifier.clickable { uriHandler.openUri(trendingRepo.url) })
                    Row(
                        Modifier
                            .width(200.dp)
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .clip(CircleShape)
                                .background(Color.Blue)
                        )
                        Text(text = trendingRepo.language)
                        Image(
                            painter = painterResource(id = R.drawable.baseline_star_24),
                            contentDescription = "Score"
                        )
                        Text(text = trendingRepo.score.toString())
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTrendingListItem() {
    TrendingGitRepositoriesTheme {
        TrendingListItem(
            GitRepositoryDetails(
                "Kotlin",
                avatar = "https://avatars.githubusercontent.com/u/4314092?v=4",
                description = "kotlin repo"
            ),
            false
        ) {}
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSelectedTrendingListItem() {
    TrendingGitRepositoriesTheme {
        TrendingListItem(
            GitRepositoryDetails(
                "Kotlin",
                avatar = "https://avatars.githubusercontent.com/u/4314092?v=4",
                url = "https://avatars.githubusercontent.com/u/4314092?v=4",
                description = "kotlin repo",
                language = "Python",
                score = 5.0
            ),
            true
        ) {}
    }
}