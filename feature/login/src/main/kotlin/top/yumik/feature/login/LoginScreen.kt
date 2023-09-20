package top.yumik.feature.login

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.runBlocking
import top.yumik.core.common.qrcode.QRCode
import top.yumik.core.common.result.Result
import top.yumik.core.designsystem.component.ScTopAppBar
import top.yumik.core.designsystem.image.ScIcons
import top.yumik.core.designsystem.preview.ThemePreviews
import top.yumik.core.designsystem.theme.ScTheme

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
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .consumeWindowInsets(padding)
                .windowInsetsPadding(
                    WindowInsets
                        .safeDrawing
                        .only(WindowInsetsSides.Horizontal)
                )
        ) {
            if (maxHeight > 400.dp) {
                PortraitQRCodeScanner(
                    modifier = Modifier.fillMaxSize(),
                    bitmap = uiState.bitmap
                )
            } else {
                LandscapeQRCodeScanner(
                    modifier = Modifier.fillMaxSize(),
                    bitmap = uiState.bitmap
                )
            }
        }
    }
}

@Composable
private fun PortraitQRCodeScanner(
    modifier: Modifier = Modifier,
    bitmap: Result<Bitmap>
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        DisplayQRCodeBitmap(bitmap = bitmap)
        Spacer(modifier = Modifier.height(48.dp))
        HelperQRCodeBitmap()
    }
}

@Composable
private fun LandscapeQRCodeScanner(
    modifier: Modifier = Modifier,
    bitmap: Result<Bitmap>
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        DisplayQRCodeBitmap(bitmap = bitmap)
        Spacer(modifier = Modifier.width(48.dp))
        HelperQRCodeBitmap()
    }
}

@Composable
private fun DisplayQRCodeBitmap(
    modifier: Modifier = Modifier,
    bitmap: Result<Bitmap>
) {
    Column(
        modifier = modifier.widthIn(max = 288.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "请使用网易云APP扫描二维码\n" +
                    "授权去登录星云应用",
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
                    CircularProgressIndicator()
                }

                is Result.Success -> {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        bitmap = runBlocking {
                            QRCode.generate("https://www.baidu.com").asImageBitmap()
                        },
                        contentDescription = "二维码",
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                    )
                }

                is Result.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = ScIcons.WifiOff,
                            contentDescription = "网络错误"
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "网络错误",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "星云 StarCloud",
            style = MaterialTheme.typography.labelLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun HelperQRCodeBitmap(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "二维码失效或扫码失败",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
        TextButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = ScIcons.Refresh,
                contentDescription = "刷新二维码"
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = "刷新二维码",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoginTopAppBar(onNavigationClick: () -> Unit) {
    ScTopAppBar(
        title = "扫码登录",
        navigationIcon = ScIcons.ArrowBack,
        navigationIconContentDescription = "返回",
        onNavigationClick = onNavigationClick
    )
}

@Composable
@ThemePreviews
private fun LoginScreenPreview() {
    ScTheme {
        LoginScreen(
            uiState = LoginState(
                bitmap = Result.Error(Exception("NullPointException in LoginScreenPreview"))
            ),
            onRefreshQRCodeBitmap = { /*TODO*/ },
            onNavigationClick = { /*TODO*/ }
        )
    }
}