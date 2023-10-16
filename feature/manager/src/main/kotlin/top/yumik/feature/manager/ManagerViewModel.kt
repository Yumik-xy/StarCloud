package top.yumik.feature.manager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import top.yumik.core.data.repository.UserDetailRepository
import top.yumik.core.data.repository.UserPreferencesRepository
import top.yumik.core.model.BrandColor
import top.yumik.core.model.DarkTheme
import javax.inject.Inject

private const val TAG = "ManagerViewModel"

@HiltViewModel
class ManagerViewModel @Inject constructor(
    private val userDetailRepository: UserDetailRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    val uiState = combine(
        userDetailRepository.userDetail,
        userPreferencesRepository.userPreferences
    ) { userDetail, userPreferences ->
        ManagerUiState.Success(
            userDetail = userDetail,
            userPreferences = userPreferences
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ManagerUiState.Loading,
    )

    fun setBrandColor(brandColor: BrandColor) {
        viewModelScope.launch {
            userPreferencesRepository.setBrandColor(brandColor)
        }
    }

    fun setDarkTheme(darkTheme: DarkTheme) {
        viewModelScope.launch {
            userPreferencesRepository.setDarkTheme(darkTheme)
        }
    }

    fun logout() {
        viewModelScope.launch {
            userDetailRepository.clearUserDetail()
        }
    }
}