package top.yumik.feature.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import top.yumik.core.data.fake.fakeCategories
import top.yumik.core.data.fake.fakeMyCategory
import top.yumik.core.data.fake.fakeUserDetail
import top.yumik.core.database.model.MyCategory
import top.yumik.core.designsystem.component.ScAsyncImage
import top.yumik.core.designsystem.preview.DevicePreviews
import top.yumik.core.designsystem.preview.ScPreview
import top.yumik.core.designsystem.preview.ThemePreviews
import top.yumik.core.ui.GuestScreen
import top.yumik.core.ui.LoadingScreen

@Composable
fun CategoryRoute(
    modifier: Modifier = Modifier,
    onNavigateToLogin: () -> Unit,
    viewModel: CategoryViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    CategoryScreen(
        modifier = modifier,
        uiState = uiState,
        onNavigateToLogin = onNavigateToLogin,
        onRefreshPage = viewModel::refreshPage
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    uiState: CategoryUiState,
    onNavigateToLogin: () -> Unit,
    onRefreshPage: () -> Unit
) {
    when (uiState) {
        is CategoryUiState.Loading -> {
            LoadingScreen(
                modifier = modifier
            )
        }

        is CategoryUiState.Success -> {
            if (uiState.userDetail.isGuest()) {
                GuestScreen(
                    modifier = modifier,
                    onNavigateToLogin = onNavigateToLogin
                )
            } else {
                CategoryListScreen(
                    modifier = modifier,
                    pageRefreshing = uiState.pageRefreshing,
                    myCategories = uiState.myCategories,
                    onRefreshPage = onRefreshPage
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun CategoryListScreen(
    modifier: Modifier = Modifier,
    pageRefreshing: Boolean,
    myCategories: List<MyCategory>,
    onRefreshPage: () -> Unit
) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = pageRefreshing,
        onRefresh = onRefreshPage
    )

    Box(
        modifier = modifier.pullRefresh(
            state = pullRefreshState
        )
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = rememberLazyListState(),
            contentPadding = PaddingValues(vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                MyCategory(myCategories = myCategories)
            }
        }
        PullRefreshIndicator(
            modifier = Modifier.align(Alignment.TopCenter),
            refreshing = pageRefreshing,
            state = pullRefreshState,
            scale = true
        )

    }
}

@Composable
private fun MyCategory(
    modifier: Modifier = Modifier,
    myCategories: List<MyCategory>
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 24.dp),
            text = stringResource(R.string.category_screen_title_my_categories),
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold
        )
        LazyRow(
            state = rememberLazyListState(),
            contentPadding = PaddingValues(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = myCategories,
                key = { it.id }
            ) { myCategory ->
                MyCategoryItem(
                    coverImgUrl = myCategory.coverImgUrl,
                    name = myCategory.name
                )
            }
        }
    }
}

@Composable
private fun MyCategoryItem(
    modifier: Modifier = Modifier,
    coverImgUrl: String,
    name: String
) {
    Column(
        modifier = modifier.width(96.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        ScAsyncImage(
            modifier = Modifier
                .size(96.dp)
                .clip(RoundedCornerShape(12.dp)),
            imageUrl = coverImgUrl,
            contentDescription = stringResource(R.string.category_screen_category_cover)
        )
        Text(
            text = name,
            style = MaterialTheme.typography.labelMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}


@Composable
@ThemePreviews
private fun MyCategoryItemPreview() {
    ScPreview {
        MyCategoryItem(
            coverImgUrl = fakeMyCategory.coverImgUrl,
            name = fakeMyCategory.name
        )
    }
}

@Composable
@DevicePreviews
private fun CategoryScreenPreview() {
    ScPreview {
        CategoryScreen(
            modifier = Modifier.fillMaxSize(),
            uiState = CategoryUiState.Success(
                userDetail = fakeUserDetail,
                pageRefreshing = false,
                myCategories = fakeCategories
            ),
            onNavigateToLogin = {},
            onRefreshPage = {}
        )
    }
}