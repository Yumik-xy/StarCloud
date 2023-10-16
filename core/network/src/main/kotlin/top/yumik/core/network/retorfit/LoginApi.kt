package top.yumik.core.network.retorfit

import retrofit2.http.POST
import retrofit2.http.Query
import top.yumik.core.network.interceptor.Timestamp
import top.yumik.core.network.model.NetworkLoginQrCheck
import top.yumik.core.network.model.NetworkLoginQrCreate
import top.yumik.core.network.model.NetworkLoginQrKey

/**
 * 登录接口统一增加`@Timestamp`参数
 */
internal interface LoginApi {

    /**
     * 调用接口，获取扫码登录的二维码Key
     */
    @POST("login/qr/key")
    @Timestamp
    suspend fun fetchQrKey(): NetworkLoginQrKey

    /**
     * 调用接口，获取扫码登录的二维码验证url，通过该url生产二维码，即可扫码登录
     */
    @POST("login/qr/create")
    @Timestamp
    suspend fun fetchQrAuthUrl(
        @Query("key") key: String
    ): NetworkLoginQrCreate

    /**
     * 调用接口，检查扫码登录验证状态，返回Code信息如下：
     * - 800 为二维码过期
     * - 801 为等待扫码
     * - 802 为待确认
     * - 803 为授权登录成功(803 状态码下会返回 cookies)
     */
    @POST("login/qr/check")
    @Timestamp
    suspend fun checkAuthState(
        @Query("key") key: String,
        @Query("noCookie") noCookie: Boolean = true
    ): NetworkLoginQrCheck
}