package top.yumik.feature.login

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import top.yumik.core.common.canceljob.cancelableJob
import top.yumik.core.common.result.Result
import top.yumik.core.data.repository.UserDetailRepository
import top.yumik.core.data.util.NetworkMonitor
import top.yumik.core.domain.CheckQrAuthStateUseCase
import top.yumik.core.domain.FetchQrAuthBitmapUseCase
import top.yumik.core.network.retorfit.RetrofitScNetwork
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    networkMonitor: NetworkMonitor,
    private val networkDataSource: RetrofitScNetwork,
    private val fetchQrAuthBitmapUseCase: FetchQrAuthBitmapUseCase,
    private val checkQrAuthStateUseCase: CheckQrAuthStateUseCase,
    private val userDetailRepository: UserDetailRepository
) : ViewModel() {

    private val bitmapFlow: MutableStateFlow<Result<Bitmap>> = MutableStateFlow(Result.Loading)

    private val eventChannel = Channel<LoginEvent>(Channel.BUFFERED)
    val event = eventChannel.receiveAsFlow()

    val state = combine(bitmapFlow, networkMonitor.isOnline) { bitmapResult, isOnlineState ->
        LoginState(
            isOnline = isOnlineState,
            bitmap = bitmapResult
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = LoginState()
    )

    init {
        refreshLoginBitmap()
    }

    fun refreshLoginBitmap() {
        viewModelScope.cancelableJob("refresh-login-bitmap") {
            bitmapFlow.update { Result.Loading }
            val key = networkDataSource.fetchQrKey()
            val bitmap = fetchQrAuthBitmapUseCase(key)

            checkQrAuthStateUseCase(key)
                .distinctUntilChanged()
                .collect { cookieResult ->
                    when (cookieResult) {
                        is Result.Loading -> {
                            bitmapFlow.update { Result.Success(bitmap) }
                        }

                        is Result.Error -> {
                            bitmapFlow.update { Result.Error(cookieResult.exception) }
                        }

                        is Result.Success -> {
                            userDetailRepository.setCookie(cookie = cookieResult.value)
                            fetchUserDetail(cookieResult.value)

                            eventChannel.send(LoginEvent.Authenticated)
                        }
                    }
                }
        }
    }

    private suspend fun fetchUserDetail(cookie: String) {
        val userDetail = networkDataSource.fetchMyUserDetail(cookie)
        userDetailRepository.setUserDetail(
            userId = userDetail.profile.userId,
            nickname = userDetail.profile.nickname,
            avatarUrl = userDetail.profile.avatarUrl,
            vipType = userDetail.profile.vipType,
            level = userDetail.level,
            fans = userDetail.profile.fans,
            follows = userDetail.profile.follows
        )
    }
}

data class LoginState(
    val isOnline: Boolean = true,
    val bitmap: Result<Bitmap> = Result.Loading
)

sealed interface LoginEvent {
    data object Authenticated : LoginEvent
}