package com.tafreshiali.weatherapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.tafreshiali.weatherapp.R
import com.tafreshiali.weatherapp.presentation.theme.design_system.WeatherAppTheme

@Composable
fun ErrorStateComponent(modifier: Modifier = Modifier) {
    val lottieComposition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.pull_to_refresh_animation)
    )
    val progress by animateLottieCompositionAsState(
        isPlaying = true,
        composition = lottieComposition,
        iterations = LottieConstants.IterateForever
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        LottieAnimation(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.35f),
            composition = lottieComposition,
            progress = { progress },
        )
        Text(
            "Opps, something went wrong pull to refresh",
            style = WeatherAppTheme.typography.regular14.copy(textAlign = TextAlign.Center)
        )
    }
}

@Preview
@Composable
private fun ErrorStateComponentPreview() {
    WeatherAppTheme {
        ErrorStateComponent()
    }
}