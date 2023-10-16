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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import top.yumik.core.designsystem.image.ScIcons
import top.yumik.core.designsystem.preview.ScPreview
import top.yumik.core.designsystem.preview.ThemePreviews

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
            text = stringResource(R.string.guest_screen_login_title),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onNavigateToLogin) {
            Text(text = stringResource(R.string.guest_screen_login_btn))
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = ScIcons.ArrowForward,
                contentDescription = stringResource(R.string.guest_screen_login_btn)
            )
        }
    }
}

@Composable
@ThemePreviews
private fun GuestScreenPreview() {
    ScPreview {
        GuestScreen(
            onNavigateToLogin = { /*TODO*/ }
        )
    }
}