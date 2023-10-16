package top.yumik.core.network.retorfit

import top.yumik.core.network.model.NetworkLoginQrCheck
import top.yumik.core.network.model.NetworkUserDetail
import top.yumik.core.network.model.NetworkUserPlaylist

internal interface ScNetworkDataSource {

    suspend fun fetchQrKey(): String

    suspend fun fetchQrAuthUrl(key: String): String

    suspend fun checkQrAuthState(key: String): NetworkLoginQrCheck

    suspend fun fetchMyUserDetail(cookie: String): NetworkUserDetail

    suspend fun fetchUserDetail(cookie: String, userId: Int): NetworkUserDetail

    suspend fun fetchCategoryList(userId: Int): NetworkUserPlaylist
}