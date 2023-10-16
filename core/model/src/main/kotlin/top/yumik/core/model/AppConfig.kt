package top.yumik.core.model

data class AppConfig(
    val isDebug: Boolean,
    val versionCode: Int,
    val versionName: String,
    val buildTime: Long,
    val openSourceLink: String
)
