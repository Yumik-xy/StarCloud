package top.yumik.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yumik.core.designsystem.image.ScIcons
import top.yumik.core.designsystem.preview.ThemePreviews
import top.yumik.core.designsystem.theme.ScTheme

@Composable
fun GuestScreen(
    modifier: Modifier = Modifier,
    onNavigateToLogin: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "登录体验完整功能",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onNavigateToLogin) {
            Text(text = "去登录")
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = ScIcons.ArrowForward,
                contentDescription = "去登录"
            )
        }
    }
}

@Composable
@ThemePreviews
private fun GuestScreenPreview() {
    ScTheme {
        GuestScreen(
            onNavigateToLogin = { /*TODO*/ }
        )
    }
}