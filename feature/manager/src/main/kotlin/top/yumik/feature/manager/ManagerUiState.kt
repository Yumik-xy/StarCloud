package top.yumik.feature.manager

import top.yumik.core.model.UserDetail
import top.yumik.core.model.UserPreferences

sealed interface ManagerUiState {
    data object Loading : ManagerUiState
    data class Success(
        val userDetail: UserDetail,
        val userPreferences: UserPreferences
    ) : ManagerUiState
}