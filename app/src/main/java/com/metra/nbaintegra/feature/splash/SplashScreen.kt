package com.metra.nbaintegra.feature.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.metra.nbaintegra.R
import com.metra.nbaintegra.core.ui.NbaColors
import com.metra.nbaintegra.core.ui.NbaDimensions
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit,
    viewModel: SplashViewModel = koinViewModel(),
) {
    val state by viewModel.states.collectAsStateWithLifecycle()

    LaunchedEffect(state.loading) {
        if (!state.loading) {
            onSplashFinished()
        }
    }

    SplashScreenImpl(
        state = state,
    )
}

@Composable
private fun SplashScreenImpl(
    modifier: Modifier = Modifier,
    state: SplashViewModel.State,
) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.basketball),
    )

    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = state.loading,
        iterations = Int.MAX_VALUE,
    )

    Surface(
        modifier = modifier.fillMaxSize(),
        shape = RoundedCornerShape(NbaDimensions.sizeS),
        color = NbaColors.background,
        contentColor = NbaColors.chrome900,
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(NbaDimensions.sizeM),
            contentAlignment = Alignment.Center,
        ) {
            LottieAnimation(
                composition = composition,
                progress = { progress },
            )
        }
    }
}
