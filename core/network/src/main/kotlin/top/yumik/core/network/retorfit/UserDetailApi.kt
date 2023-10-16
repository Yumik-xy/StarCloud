package top.yumik.core.network.retorfit

import retrofit2.http.POST
import retrofit2.http.Query
import top.yumik.core.network.model.NetworkUserAccount
import top.yumik.core.network.model.NetworkUserDetail

internal interface UserDetailApi {

    /**
     * 调用接口，获取当前登录用户的UserId
     */
    @POST("user/account")
    suspend fun fetchUserAccount(
        @Query("cookie") cookie: String
    ): NetworkUserAccount

    /**
     * 调用接口，获取指定用户的详情信息
     */
    @POST("user/detail")
    suspend fun fetchUserDetail(
        @Query("cookie") cookie: String,
        @Query("uid") userId: Int
    ): NetworkUserDetail
}