import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import top.yumik.gradle.convention.configureGradleManagedDevices
import top.yumik.gradle.convention.libs

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("starcloud.android.library")
                apply("starcloud.android.hilt")
            }
            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
                configureGradleManagedDevices(this)
            }

            dependencies {
                add("implementation", project(":core:common"))
                add("implementation", project(":core:data"))
                add("implementation", project(":core:database"))
                add("implementation", project(":core:designsystem"))
                add("implementation", project(":core:domain"))
                add("implementation", project(":core:model"))
                add("implementation", project(":core:network"))
                add("implementation", project(":core:ui"))

                add("implementation", libs.findLibrary("hilt.navigationCompose").get())
                add("implementation", libs.findLibrary("lifecycle.runtimeCompose").get())
                add("implementation", libs.findLibrary("lifecycle.viewModelCompose").get())

                add("implementation", libs.findLibrary("coroutines.android").get())
            }
        }
    }
}
