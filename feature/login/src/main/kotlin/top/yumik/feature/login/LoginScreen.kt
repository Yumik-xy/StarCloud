package top.yumik.feature.login

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.runBlocking
import top.yumik.core.common.qrcode.QRCode
import top.yumik.core.common.result.Result
import top.yumik.core.designsystem.component.ScTopAppBar
import top.yumik.core.designsystem.image.ScIcons
import top.yumik.core.designsystem.preview.DevicePreviews
import top.yumik.core.designsystem.preview.ScPreview
import top.yumik.core.designsystem.util.noRipperClickable

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun LoginScreen(
    modifier: Modifier = Modifier,
    uiState: LoginState,
    onRefreshQRCodeBitmap: () -> Unit,
    onNavigationClick: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            LoginTopAppBar(
                onNavigationClick = onNavigationClick
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .consumeWindowInsets(padding)
                .windowInsetsPadding(
                    WindowInsets
                        .safeDrawing
                        .only(WindowInsetsSides.Horizontal)
                ),
            contentAlignment = Alignment.Center
        ) {
            DisplayQRCodeBitmap(
                bitmap = uiState.bitmap,
                onRefreshQRCodeBitmap = onRefreshQRCodeBitmap
            )
        }
    }
}

@Composable
private fun DisplayQRCodeBitmap(
    modifier: Modifier = Modifier,
    bitmap: Result<Bitmap>,
    onRefreshQRCodeBitmap: () -> Unit
) {
    Column(
        modifier = modifier.widthIn(max = 288.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.login_screen_title),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))
        Box(
            modifier = Modifier.size(120.dp),
            contentAlignment = Alignment.Center
        ) {
            when (bitmap) {
                is Result.Loading -> {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                is Result.Success -> {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        bitmap = bitmap.value.asImageBitmap(),
                        contentDescription = stringResource(R.string.login_screen_qr_code_description),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                    )
                }

                is Result.Error -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .noRipperClickable(onClick = onRefreshQRCodeBitmap),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = ScIcons.WifiOff,
                            contentDescription = stringResource(R.string.login_screen_network_error_description)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = stringResource(R.string.login_screen_network_error),
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.login_screen_app_full_name),
            style = MaterialTheme.typography.labelLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoginTopAppBar(onNavigationClick: () -> Unit) {
    ScTopAppBar(
        title = stringResource(R.string.login_screen_toolbar_title),
        navigationIcon = ScIcons.ArrowBack,
        navigationIconContentDescription = stringResource(R.string.login_screen_toolbar_navigation_description),
        onNavigationClick = onNavigationClick
    )
}

@Composable
@DevicePreviews
private fun LoginScreenPreview() {
    ScPreview {
        LoginScreen(
            uiState = LoginState(
                bitmap = Result.Success(
                    runBlocking { QRCode.generate("Star Cloud") }
                )
            ),
            onRefreshQRCodeBitmap = { /*TODO*/ },
            onNavigationClick = { /*TODO*/ }
        )
    }
}