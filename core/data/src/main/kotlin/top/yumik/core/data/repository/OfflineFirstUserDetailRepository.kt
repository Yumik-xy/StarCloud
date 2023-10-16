package top.yumik.core.data.repository

import kotlinx.coroutines.flow.Flow
import top.yumik.core.datastore.UserDetailDataSource
import top.yumik.core.model.UserDetail
import javax.inject.Inject

class OfflineFirstUserDetailRepository @Inject constructor(
    private val userDetailDataSource: UserDetailDataSource
) : UserDetailRepository {

    override val userDetail: Flow<UserDetail> = userDetailDataSource.userDetail

    override suspend fun setCookie(cookie: String) {
        userDetailDataSource.setCookie(cookie)
    }

    override suspend fun setUserDetail(
        userId: Int,
        nickname: String,
        avatarUrl: String,
        vipType: Int,
        level: Int,
        fans: Int,
        follows: Int
    ) {
        userDetailDataSource.setUserDetail(
            userId = userId,
            nickname = nickname,
            avatarUrl = avatarUrl,
            vipType = vipType,
            level = level,
            fans = fans,
            follows = follows
        )
    }

    override suspend fun clearUserDetail() {
        userDetailDataSource.clearUserDetail()
    }
}