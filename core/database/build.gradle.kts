plugins {
    id("starcloud.android.library")
    id("starcloud.android.hilt")
    id("starcloud.android.room")
}

android {
    namespace = "top.yumik.core.database"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    testOptions {
        unitTests {
            isReturnDefaultValues = true
        }
    }
}

dependencies {

    implementation(project(":core:model"))

    implementation(libs.core.ktx)
    implementation(libs.coroutines.android)

    implementation(libs.moshi.kotlin)
    ksp(libs.moshi.kotlinCodegen)
    implementation(libs.moshi.adapters)
}