plugins {
    id("starcloud.android.library")
    id("starcloud.android.hilt")
}

android {
    namespace = "top.yumik.core.data"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {

    implementation(project(":core:common"))
    implementation(project(":core:database"))
    implementation(project(":core:datastore"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:model"))
    implementation(project(":core:network"))

    implementation(libs.core.ktx)
    implementation(libs.coroutines.android)
}