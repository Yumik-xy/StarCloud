plugins {
    id("starcloud.android.library")
}

android {
    namespace = "top.yumik.core.common"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.activity.compose)
    implementation(libs.zxing)
    implementation(libs.coroutines.android)
    implementation(libs.androidx.startup)
}