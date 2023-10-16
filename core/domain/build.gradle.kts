plugins {
    id("starcloud.android.library")
    id("starcloud.android.hilt")
}

android {
    namespace = "top.yumik.core.domain"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {

    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:network"))
    implementation(project(":core:model"))

    implementation(libs.core.ktx)
    implementation(libs.coroutines.android)
}