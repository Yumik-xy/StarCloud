package top.yumik.feature.manager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import top.yumik.core.designsystem.component.ScAsyncImage
import top.yumik.core.designsystem.image.ScIcons
import top.yumik.core.designsystem.preview.ThemePreviews
import top.yumik.core.designsystem.theme.ScTheme
import top.yumik.core.ui.GuestScreen

@Composable
fun ManagerRoute(
    modifier: Modifier = Modifier
) {
    ManagerScreen(
        modifier = modifier,
        isGuest = false,
        onNavigateToLogin = {/* TODO */ }
    )
}

@Composable
fun ManagerScreen(
    modifier: Modifier = Modifier,
    isGuest: Boolean,
    onNavigateToLogin: () -> Unit
) {
    val scrollScope = rememberScrollState()

    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(scrollScope),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (isGuest) {
            GuestScreen(
                modifier = Modifier.fillMaxSize(),
                onNavigateToLogin = onNavigateToLogin
            )
        } else {
            UserInfoScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInfoScreen(
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier,
        onClick = {

        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ScAsyncImage(
                modifier = Modifier.size(48.dp),
                imageUrl = "",
                contentDescription = "用户头像"
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                modifier = Modifier.weight(1F),
                text = "软软团团子",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                    text = "Vip.11",
                    style = MaterialTheme.typography.bodyMedium,
                    fontStyle = FontStyle.Italic
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = ScIcons.ArrowForwardIos,
                contentDescription = ""
            )
        }
    }
}

@Composable
@ThemePreviews
fun ManagerScreenPreview() {
    ScTheme {
        ManagerScreen(
            modifier = Modifier.fillMaxSize(),
            isGuest = false,
            onNavigateToLogin = { /* TODO */ }
        )
    }
}


@Composable
@ThemePreviews
fun ManagerScreenGuestPreview() {
    ScTheme {
        ManagerScreen(
            modifier = Modifier.fillMaxSize(),
            isGuest = true,
            onNavigateToLogin = { /* TODO */ }
        )
    }
}