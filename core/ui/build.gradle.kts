plugins {
    id("starcloud.android.library")
    id("starcloud.android.library.compose")
}

android {
    namespace = "top.yumik.core.ui"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {

    implementation(project(":core:designsystem"))

    implementation(libs.core.ktx)
    implementation(libs.zxing)
}