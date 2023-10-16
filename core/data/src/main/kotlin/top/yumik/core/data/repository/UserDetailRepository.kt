package top.yumik.core.data.repository

import kotlinx.coroutines.flow.Flow
import top.yumik.core.model.UserDetail

interface UserDetailRepository {

    val userDetail: Flow<UserDetail>

    suspend fun setCookie(cookie: String)

    suspend fun setUserDetail(
        userId: Int,
        nickname: String,
        avatarUrl: String,
        vipType: Int,
        level: Int,
        fans: Int,
        follows: Int
    )

    suspend fun clearUserDetail()
}