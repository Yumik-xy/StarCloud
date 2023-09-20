package top.yumik.gradle.convention

enum class ScBuildType(val applicationIdSuffix: String? = null) {
    DEBUG(".debug"),
    RELEASE,
}
