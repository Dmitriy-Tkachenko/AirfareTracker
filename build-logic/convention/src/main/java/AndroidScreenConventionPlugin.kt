import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import ru.tk4dmitriy.build_logic.convention.gradle_plugins.libraryExtension
import ru.tk4dmitriy.build_logic.convention.gradle_plugins.libs

class AndroidScreenConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.run {
                apply("app.android.library")
            }

            requireNotNull(libraryExtension).apply {
                buildFeatures {
                    viewBinding = true
                }
            }

            dependencies {
                add("implementation", project(":module_injector"))
                add("implementation", project(":core:res"))

                add("implementation", libs.findLibrary("androidx.core.ktx").get())
                add("implementation", libs.findLibrary("androidx.appcompat").get())
                add("implementation", libs.findLibrary("google.material").get())
            }
        }
    }
}