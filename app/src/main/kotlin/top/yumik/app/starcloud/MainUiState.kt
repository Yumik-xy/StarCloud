package top.yumik.app.starcloud

import top.yumik.core.model.UserPreferences


sealed interface MainUiState {
    data object Loading : MainUiState
    data class Success(val userPreferences: UserPreferences) : MainUiState
}