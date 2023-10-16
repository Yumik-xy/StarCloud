package top.yumik.app.starcloud

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import top.yumik.app.starcloud.ui.MainScreen
import top.yumik.core.designsystem.color.ScColors
import top.yumik.core.designsystem.theme.ScTheme
import top.yumik.core.model.BrandColor
import top.yumik.core.model.DarkTheme

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        var uiState: MainUiState by mutableStateOf(MainUiState.Loading)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState
                    .onEach {
                        uiState = it
                    }
                    .collect()
            }
        }

        splashScreen.setKeepOnScreenCondition {
            when (uiState) {
                is MainUiState.Loading -> true
                is MainUiState.Success -> false
            }
        }

        enableEdgeToEdge()

        setContent {
            val darkTheme = getDarkTheme(uiState)

            DisposableEffect(darkTheme) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.auto(
                        lightScrim = Color.TRANSPARENT,
                        darkScrim = Color.TRANSPARENT,
                    ) { darkTheme },
                    navigationBarStyle = SystemBarStyle.auto(
                        lightScrim = lightScrim,
                        darkScrim = darkScrim,
                    ) { darkTheme },
                )
                onDispose {}
            }

            ScTheme(
                darkTheme = darkTheme,
                customColor = getCustomColor(uiState)
            ) {
                MainScreen(
                    windowSizeClass = calculateWindowSizeClass(this)
                )
            }
        }
    }
}

@Composable
private fun getCustomColor(uiState: MainUiState) = when (uiState) {
    is MainUiState.Loading -> ScColors.DEFAULT
    is MainUiState.Success -> when (uiState.userPreferences.brandColor) {
        BrandColor.DEFAULT -> ScColors.DEFAULT
        BrandColor.BLUE -> ScColors.BLUE
        BrandColor.RED -> ScColors.RED
        BrandColor.GREEN -> ScColors.GREEN
        BrandColor.YELLOW -> ScColors.YELLOW
    }
}

@Composable
private fun getDarkTheme(uiState: MainUiState) = when (uiState) {
    is MainUiState.Loading -> isSystemInDarkTheme()
    is MainUiState.Success -> when (uiState.userPreferences.darkTheme) {
        DarkTheme.FOLLOW_SYSTEM -> isSystemInDarkTheme()
        DarkTheme.LIGHT -> false
        DarkTheme.DARK -> true
    }
}

private val lightScrim = Color.argb(0xe6, 0xFF, 0xFF, 0xFF)

private val darkScrim = Color.argb(0x80, 0x1b, 0x1b, 0x1b)
