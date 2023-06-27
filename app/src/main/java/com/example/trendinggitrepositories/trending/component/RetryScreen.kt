package com.example.trendinggitrepositories.trending.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.trendinggitrepositories.R
import com.example.trendinggitrepositories.ui.theme.TrendingGitRepositoriesTheme

@Composable
fun RetryScreen(onRetryClick: () -> Unit) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.retry_animation))

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = composition,
            modifier = Modifier.fillMaxWidth(),
            iterations = LottieConstants.IterateForever
        )
        Text(
            modifier = Modifier.padding(top = 30.dp),
            text = stringResource(id = R.string.error_title),
            style = TextStyle(fontWeight = FontWeight.Bold)
        )
        Text(
            text = stringResource(id = R.string.error_subtitle),
        )
        Spacer(modifier = Modifier.weight(1f))
        OutlinedButton(
            onClick = onRetryClick,
            border = BorderStroke(2.dp, Color.Green),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.retry),
                color = Color.Green,
            )
        }
    }
}

@Preview
@Composable
fun PreviewRetryScreen() {
    TrendingGitRepositoriesTheme {
        RetryScreen {

        }
    }
}