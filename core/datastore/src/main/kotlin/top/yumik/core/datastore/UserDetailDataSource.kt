package top.yumik.core.datastore

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.map
import top.yumik.core.model.UserDetail
import javax.inject.Inject

class UserDetailDataSource @Inject constructor(
    private val userDetailDataStore: DataStore<UserDetailProto>
) {

    val userDetail = userDetailDataStore.data
        .map {
            UserDetail(
                cookie = it.cookie,
                userId = it.userId,
                nickname = it.nickname,
                avatarUrl = it.avatarUrl,
                vipType = it.vipType,
                level = it.level,
                fans = it.fans,
                follows = it.follows
            )
        }

    suspend fun setCookie(cookie: String) {
        userDetailDataStore.updateData {
            it.copy {
                this.cookie = cookie
            }
        }
    }

    suspend fun setUserDetail(
        userId: Int,
        nickname: String,
        avatarUrl: String,
        vipType: Int,
        level: Int,
        fans: Int,
        follows: Int
    ) {
        userDetailDataStore.updateData {
            it.copy {
                this.userId = userId
                this.nickname = nickname
                this.avatarUrl = avatarUrl
                this.vipType = vipType
                this.level = level
                this.fans = fans
                this.follows = follows
            }
        }
    }

    suspend fun clearUserDetail() {
        userDetailDataStore.updateData {
            it.copy {
                this.clearUserId()
                this.clearNickname()
                this.clearAvatarUrl()
                this.clearVipType()
                this.clearLevel()
                this.clearFans()
                this.clearFollows()
            }
        }
    }
}