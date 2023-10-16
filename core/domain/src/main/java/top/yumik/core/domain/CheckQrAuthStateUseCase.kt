package top.yumik.core.domain

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import top.yumik.core.common.result.Result
import top.yumik.core.network.model.NetworkLoginQrCheck
import top.yumik.core.network.retorfit.RetrofitScNetwork
import javax.inject.Inject

/** 循环检查扫码状态的间隔 */
private const val INTERVAL = 5000L

class CheckQrAuthStateUseCase @Inject constructor(
    private val networkDataSource: RetrofitScNetwork
) {

    operator fun invoke(key: String): Flow<Result<String>> = flow {
        while (true) {
            val authState = networkDataSource.checkQrAuthState(key)
            when (authState.code) {
                NetworkLoginQrCheck.Code.EXPIRED -> {
                    emit(Result.Error())
                    break
                }

                NetworkLoginQrCheck.Code.NOT_SCAN, NetworkLoginQrCheck.Code.WAIT_CONFIRM -> {
                    emit(Result.Loading)
                    delay(INTERVAL)
                }

                NetworkLoginQrCheck.Code.CONFIRMED -> {
                    emit(Result.Success(authState.cookie))
                    break
                }
            }
        }

    }.catch {
        emit(Result.Error(it))
    }.cancellable()
}