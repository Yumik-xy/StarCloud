import top.yumik.gradle.convention.ScBuildType

plugins {
    id("starcloud.android.application")
    id("starcloud.android.application.compose")
    id("starcloud.android.application.flavors")
    id("starcloud.android.hilt")
}

android {
    namespace = "top.yumik.app.starcloud"

    defaultConfig {
        applicationId = "top.yumik.app.starcloud"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = ScBuildType.DEBUG.applicationIdSuffix
        }
        release {
            isMinifyEnabled = true
            applicationIdSuffix = ScBuildType.RELEASE.applicationIdSuffix
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            signingConfig = signingConfigs.getByName("debug")
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.activity.compose)

    implementation(project(":core:designsystem"))

    implementation(project(":feature:login"))
    implementation(project(":feature:manager"))

    testImplementation(libs.junit)
}