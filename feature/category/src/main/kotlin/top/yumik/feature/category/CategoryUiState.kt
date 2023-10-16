package top.yumik.feature.category

import top.yumik.core.database.model.MyCategory
import top.yumik.core.model.UserDetail

sealed interface CategoryUiState {
    data object Loading : CategoryUiState
    data class Success(
        val userDetail: UserDetail,
        val pageRefreshing: Boolean,
        val myCategories: List<MyCategory>
    ) : CategoryUiState
}