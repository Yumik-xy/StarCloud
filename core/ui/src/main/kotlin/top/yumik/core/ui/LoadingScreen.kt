package top.yumik.core.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import top.yumik.core.designsystem.preview.DevicePreviews
import top.yumik.core.designsystem.preview.ScPreview

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.page_loading),
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
@DevicePreviews
private fun LoadingScreenPreview() {
    ScPreview {
        LoadingScreen(
            modifier = Modifier.fillMaxSize()
        )
    }
}