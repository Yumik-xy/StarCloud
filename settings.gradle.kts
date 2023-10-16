pluginManagement {
    includeBuild("build-logic")
    repositories {
        maven(url = "https://maven.aliyun.com/repository/google")
        maven(url = "https://maven.aliyun.com/repository/public")
        maven(url = "https://maven.aliyun.com/repository/gradle-plugin")
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories {
        maven(url = "https://maven.aliyun.com/repository/google")
        maven(url = "https://maven.aliyun.com/repository/public")
        google()
        mavenCentral()
    }
}

rootProject.name = "StarCloud"
include(":app")
include(":core:designsystem")
include(":feature:login")
include(":core:ui")
include(":core:common")
include(":core:model")
include(":core:data")
include(":feature:manager")
include(":core:datastore")
include(":feature:category")
include(":core:network")
include(":core:domain")
include(":core:database")
