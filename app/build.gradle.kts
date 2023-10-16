import top.yumik.gradle.convention.ScBuildType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

plugins {
    id("starcloud.android.application")
    id("starcloud.android.application.compose")
    id("starcloud.android.application.flavors")
    id("starcloud.android.hilt")
}

val major = 0
val minor = 0
val patch = 1
val code = getBuildTimeCode()

android {
    namespace = "top.yumik.app.starcloud"

    defaultConfig {
        applicationId = "top.yumik.app.starcloud"
        versionCode = major * 10000 + minor * 100 + patch
        versionName = "$major.$minor.$patch.$code"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("long", "BUILD_TIME", System.currentTimeMillis().toString())
        buildConfigField("String", "GITHUB_LINK", "\"https://github.com/Yumik-xy/StarCloud\"")
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

    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.activity.compose)
    implementation(libs.hilt.navigationCompose)
    implementation(libs.androidx.navigationCompose)
    implementation(libs.compose.windowSizeClass)
    implementation(libs.androidx.windowManager)
    implementation(libs.androidx.splashscreen)

    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:datastore"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:model"))
    implementation(project(":core:network"))
    implementation(project(":core:ui"))

    implementation(project(":feature:login"))
    implementation(project(":feature:category"))
    implementation(project(":feature:manager"))

    testImplementation(libs.junit)
}

fun getBuildTimeCode(): String {
    val currentDateTime = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyMMdd")
    return currentDateTime.format(formatter)
}