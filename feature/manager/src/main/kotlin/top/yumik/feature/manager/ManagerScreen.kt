package top.yumik.feature.manager

import VerticalGrid
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import top.yumik.core.common.intent.startBrowser
import top.yumik.core.data.fake.fakeUserDetail
import top.yumik.core.data.fake.fakeUserPreferences
import top.yumik.core.data.model.color
import top.yumik.core.data.model.colorNameRes
import top.yumik.core.data.model.darkThemeNameRes
import top.yumik.core.designsystem.component.ScAsyncImage
import top.yumik.core.designsystem.component.ScSegmentedRow
import top.yumik.core.designsystem.component.SegmentedButtonLayout
import top.yumik.core.designsystem.image.ScIcons
import top.yumik.core.designsystem.preview.DevicePreviews
import top.yumik.core.designsystem.preview.ScPreview
import top.yumik.core.designsystem.preview.ThemePreviews
import top.yumik.core.designsystem.util.noRipperClickable
import top.yumik.core.model.AppConfig
import top.yumik.core.model.BrandColor
import top.yumik.core.model.DarkTheme
import top.yumik.core.model.UserDetail
import top.yumik.core.model.UserPreferences
import top.yumik.core.ui.LoadingScreen

@Composable
fun ManagerRoute(
    modifier: Modifier = Modifier,
    appConfig: AppConfig,
    navigateToLogin: () -> Unit,
    viewModel: ManagerViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    ManagerScreen(
        modifier = modifier,
        appConfig = appConfig,
        uiState = uiState,
        onNavigateToLogin = navigateToLogin,
        onLogoutClick = viewModel::logout,
        onSetBrandColor = viewModel::setBrandColor,
        onSetDarkTheme = viewModel::setDarkTheme
    )
}

@Composable
private fun ManagerScreen(
    modifier: Modifier = Modifier,
    appConfig: AppConfig,
    uiState: ManagerUiState,
    onNavigateToLogin: () -> Unit,
    onLogoutClick: () -> Unit,
    onSetBrandColor: (BrandColor) -> Unit,
    onSetDarkTheme: (DarkTheme) -> Unit
) {
    when (uiState) {
        is ManagerUiState.Loading -> {
            LoadingScreen(
                modifier = modifier
            )
        }

        is ManagerUiState.Success -> {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                UserInfoScreen(
                    userDetail = uiState.userDetail,
                    onNavigateToLogin = onNavigateToLogin
                )
                UserManagerScreen(
                    modifier = Modifier.weight(1F),
                    appConfig = appConfig,
                    userDetail = uiState.userDetail,
                    userPreferences = uiState.userPreferences,
                    onSetBrandColor = onSetBrandColor,
                    onSetDarkTheme = onSetDarkTheme,
                    onLogoutClick = onLogoutClick
                )
            }
        }
    }
}

@Composable
private fun UserInfoScreen(
    modifier: Modifier = Modifier,
    userDetail: UserDetail,
    onNavigateToLogin: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp)
            .noRipperClickable(onClick = {
                if (userDetail.isGuest()) {
                    onNavigateToLogin()
                }
            }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ScAsyncImage(
            modifier = Modifier.size(48.dp),
            imageUrl = userDetail.avatarUrl,
            contentDescription = stringResource(R.string.manager_screen_user_avatar_description)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            modifier = Modifier.weight(1F),
            text = userDetail.nickname.ifEmpty { stringResource(R.string.manager_screen_logout_nickname) },
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        if (!userDetail.isGuest()) {
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                    text = stringResource(R.string.manager_screen_user_vip, userDetail.level),
                    style = MaterialTheme.typography.bodyMedium,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}

@Composable
private fun UserManagerScreen(
    modifier: Modifier = Modifier,
    appConfig: AppConfig,
    userDetail: UserDetail,
    userPreferences: UserPreferences,
    onSetBrandColor: (BrandColor) -> Unit,
    onSetDarkTheme: (DarkTheme) -> Unit,
    onLogoutClick: () -> Unit
) {
    Surface(
        modifier = modifier,
        shadowElevation = 16.dp,
        shape = RoundedCornerShape(
            topStart = 32.dp,
            topEnd = 32.dp
        ),
        color = MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp)
    ) {
        LazyColumn(
            state = rememberLazyListState(),
            contentPadding = PaddingValues(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                ThemeManagerScreen(
                    selectedBrandColor = userPreferences.brandColor,
                    onSetBrandColor = onSetBrandColor,
                    selectedDarkTheme = userPreferences.darkTheme,
                    onSetDarkTheme = onSetDarkTheme
                )
            }
            item {
                Divider()
            }
            item {
                AboutInfoScreen(
                    appConfig = appConfig
                )
            }
            if (!userDetail.isGuest()) {
                item {
                    Divider()
                }
                item {
                    LogoutButton(
                        onLogoutClick = onLogoutClick
                    )
                }
            }
        }
    }
}

@Composable
private fun ThemeManagerScreen(
    modifier: Modifier = Modifier,
    selectedBrandColor: BrandColor,
    onSetBrandColor: (BrandColor) -> Unit,
    selectedDarkTheme: DarkTheme,
    onSetDarkTheme: (DarkTheme) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.manager_screen_title_theme_setting),
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )
            ScSegmentedRow(
                items = DarkTheme.values(),
                layout = SegmentedButtonLayout.WARP_CONTENT,
                selectedItem = selectedDarkTheme,
                onSelected = onSetDarkTheme
            ) {
                Text(text = stringResource(it.darkThemeNameRes))
            }
        }
        VerticalGrid(
            items = BrandColor.values(),
            minItemWidth = 88.dp,
            itemVerticalSpacing = 12.dp,
            itemHorizontalSpacing = 8.dp
        ) {
            ColorDisplayItem(
                modifier = Modifier.width(88.dp),
                name = stringResource(it.colorNameRes),
                lightColor = it.color.lightColorScheme,
                darkColor = it.color.darkColorScheme,
                selected = it == selectedBrandColor,
                onClick = {
                    onSetBrandColor(it)
                }
            )
        }
    }
}

@Composable
private fun ColorDisplayItem(
    modifier: Modifier = Modifier,
    name: String,
    lightColor: ColorScheme,
    darkColor: ColorScheme,
    selected: Boolean,
    onClick: () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition("colorDisplayItem")
    val angle = infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = 360F,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2400,
                easing = LinearEasing
            )
        ),
        label = "selectedSweepAngle"
    )

    val arraySize = 90
    val strokeList = Array(arraySize) { 2F / arraySize * it }
    val sweepAngleList = Array(arraySize) { 150F - 150F / arraySize * it }
    val startInterval = Array(arraySize) { 150F / arraySize * it }

    Column(
        modifier = modifier
            .noRipperClickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val primaryColor = MaterialTheme.colorScheme.primary

        Canvas(modifier = Modifier.size(48.dp)) {

            val arcSize = size.copy(
                width = size.width - 8.dp.toPx(),
                height = size.height - 8.dp.toPx()
            )
            val arcOffset = Offset(4.dp.toPx(), 4.dp.toPx())

            if (selected) {
                for (i in 0 until arraySize) {
                    drawArc(
                        color = primaryColor,
                        startAngle = startInterval[i] + angle.value,
                        sweepAngle = sweepAngleList[i],
                        useCenter = false,
                        style = Stroke(
                            width = strokeList[i].dp.toPx(),
                            cap = StrokeCap.Round
                        )
                    )
                }
            }

            drawArc(
                color = lightColor.primary,
                startAngle = 0F,
                sweepAngle = 90F,
                useCenter = true,
                size = arcSize,
                topLeft = arcOffset
            )
            drawArc(
                color = darkColor.primary,
                startAngle = 90F,
                sweepAngle = 90F,
                useCenter = true,
                size = arcSize,
                topLeft = arcOffset
            )
            drawArc(
                color = lightColor.primaryContainer,
                startAngle = 180F,
                sweepAngle = 90F,
                useCenter = true,
                size = arcSize,
                topLeft = arcOffset
            )
            drawArc(
                color = darkColor.primaryContainer,
                startAngle = 270F,
                sweepAngle = 90F,
                useCenter = true,
                size = arcSize,
                topLeft = arcOffset
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = name,
            style = MaterialTheme.typography.labelMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun AboutInfoScreen(
    modifier: Modifier = Modifier,
    appConfig: AppConfig
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = stringResource(R.string.manager_screen_title_about_mine),
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold
        )
        Column(
        ) {
            AboutInfoItem(
                onClick = {

                },
                title = stringResource(R.string.manager_screen_about_mine_version_name),
                subtitle = "v${appConfig.versionName}"
            )
            AboutInfoItem(
                onClick = {

                },
                title = stringResource(R.string.manager_screen_about_mine_detail)
            )
            AboutInfoItem(
                onClick = {
                    startBrowser(appConfig.openSourceLink)
                },
                title = stringResource(R.string.manager_screen_about_mine_open_source_link)
            )
        }
    }
}

@Composable
private fun AboutInfoItem(
    title: String,
    subtitle: String? = null,
    onClick: () -> Unit
) {
    Box(modifier = Modifier.clickable(onClick = onClick)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium
                )
            } else {
                Icon(
                    modifier = Modifier.size(12.dp),
                    imageVector = ScIcons.ArrowForwardIos,
                    contentDescription = stringResource(R.string.manager_screen_about_mine_jump_description)
                )
            }
        }
    }
}

@Composable
private fun LogoutButton(
    modifier: Modifier = Modifier,
    onLogoutClick: () -> Unit
) {
    var verifyLogoutDialogShow by remember { mutableStateOf(false) }

    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = {
            verifyLogoutDialogShow = true
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.error
        )
    ) {
        Text(
            text = stringResource(R.string.manager_screen_logout_btn),
            color = MaterialTheme.colorScheme.surface
        )
    }

    if (verifyLogoutDialogShow) {
        VerifyLogoutDialog(
            modifier = Modifier.fillMaxWidth(),
            onConfirmClick = {
                verifyLogoutDialogShow = false
                onLogoutClick()
            },
            onDismissRequest = {
                verifyLogoutDialogShow = false
            }
        )
    }
}

@Composable
private fun VerifyLogoutDialog(
    modifier: Modifier = Modifier,
    onConfirmClick: () -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(onClick = onConfirmClick) {
                Text(text = stringResource(R.string.manager_screen_logout_btn_confirm))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(text = stringResource(R.string.manager_screen_logout_btn_cancel))
            }
        },
        title = { Text(text = stringResource(R.string.manager_screen_logout_btn_title)) },
        text = { Text(text = stringResource(R.string.manager_screen_logout_btn_content)) }
    )
}

@Composable
@ThemePreviews
private fun ThemeManagerScreenPreview() {
    ScPreview {
        ThemeManagerScreen(
            modifier = Modifier.fillMaxWidth(),
            selectedBrandColor = BrandColor.BLUE,
            onSetBrandColor = {},
            selectedDarkTheme = DarkTheme.FOLLOW_SYSTEM,
            onSetDarkTheme = {}
        )
    }
}

@Composable
@ThemePreviews
private fun AboutInfoScreenPreview() {
    ScPreview {
        AboutInfoScreen(
            modifier = Modifier.fillMaxWidth(),
            appConfig = AppConfig(
                isDebug = true,
                versionCode = 1,
                versionName = "1.0.0",
                buildTime = 1,
                openSourceLink = ""
            )
        )
    }
}

@Composable
@ThemePreviews
private fun VerifyLogoutDialogPreview() {
    ScPreview {
        VerifyLogoutDialog(
            modifier = Modifier.fillMaxWidth(),
            onConfirmClick = {},
            onDismissRequest = {}
        )
    }
}

@Composable
@DevicePreviews
private fun ManagerScreenPreview() {
    ScPreview {
        ManagerScreen(
            modifier = Modifier.fillMaxSize(),
            appConfig = AppConfig(
                isDebug = true,
                versionCode = 1,
                versionName = "1.0.0",
                buildTime = 1,
                openSourceLink = ""
            ),
            uiState = ManagerUiState.Success(
                userDetail = fakeUserDetail,
                userPreferences = fakeUserPreferences
            ),
            onNavigateToLogin = {},
            onLogoutClick = {},
            onSetBrandColor = {},
            onSetDarkTheme = {}
        )
    }
}