@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("starcloud.android.library")
    id("starcloud.android.hilt")
    alias(libs.plugins.ksp)
    alias(libs.plugins.secrets)
}

android {
    namespace = "top.yumik.core.network"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = true
    }
}

secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}

dependencies {

    implementation(project(":core:common"))
    implementation(project(":core:model"))

    implementation(libs.core.ktx)
    implementation(libs.coroutines.android)

    implementation(libs.retrofit)
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
    implementation(libs.okhttp.loggingInterceptor)
    implementation(libs.moshi.retrofit)
    implementation(libs.moshi.kotlin)
    ksp(libs.moshi.kotlinCodegen)
    implementation(libs.moshi.adapters)
}