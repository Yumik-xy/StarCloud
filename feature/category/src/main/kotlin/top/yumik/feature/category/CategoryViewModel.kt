package top.yumik.feature.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import top.yumik.core.data.repository.MyCategoryRepository
import top.yumik.core.data.repository.UserDetailRepository
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    userDetailRepository: UserDetailRepository,
    private val myCategoryRepository: MyCategoryRepository
) : ViewModel() {

    private val pageRefreshing: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val uiState = combine(
        userDetailRepository.userDetail,
        pageRefreshing,
        myCategoryRepository.myCategories
    ) { userDetail, pageRefreshing, myCategoryList ->
        CategoryUiState.Success(
            userDetail = userDetail,
            pageRefreshing = pageRefreshing,
            myCategories = myCategoryList
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = CategoryUiState.Loading,
    )

    fun refreshPage() {
        viewModelScope.launch {
            pageRefreshing.update { true }
            myCategoryRepository.sync()
            pageRefreshing.update { false }
        }
    }
}