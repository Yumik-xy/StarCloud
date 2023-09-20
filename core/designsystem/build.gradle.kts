plugins {
    id("starcloud.android.library")
    id("starcloud.android.library.compose")
}

android {
    namespace = "top.yumik.core.designsystem"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {

    implementation(libs.core.ktx)

    api(libs.compose.ui)
    api(libs.compose.uiGraphics)
    api(libs.compose.uiToolingPreview)
    api(libs.compose.foundation)
    api(libs.compose.foundationLayout)
    api(libs.compose.material3)
    api(libs.compose.runtime)
    api(libs.compose.runtimeLivedata)

    implementation(libs.compose.iconsExtended)

    // Coil
    implementation(libs.coil)
    implementation(libs.coil.compose)
    implementation(libs.coil.svg)

    debugApi(libs.compose.uiTooling)
}