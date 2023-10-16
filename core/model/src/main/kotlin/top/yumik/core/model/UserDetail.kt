package top.yumik.core.model

data class UserDetail(
    val cookie: String,
    val userId: Int,
    val nickname: String,
    val avatarUrl: String,
    val vipType: Int,
    val level: Int,
    val fans: Int,
    val follows: Int
) {
    fun isGuest() = userId <= 0
}