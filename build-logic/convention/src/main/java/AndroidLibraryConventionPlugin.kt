import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import ru.tk4dmitriy.build_logic.convention.gradle_plugins.configureKotlin
import ru.tk4dmitriy.build_logic.convention.gradle_plugins.configureKotlinAndroid
import ru.tk4dmitriy.build_logic.convention.gradle_plugins.libraryExtension
import ru.tk4dmitriy.build_logic.convention.gradle_plugins.libs

class AndroidLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.run {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("com.google.devtools.ksp")
            }

            requireNotNull(libraryExtension).apply {
                configureKotlinAndroid(this)
                configureKotlin()
            }

            dependencies {
                add("implementation", libs.findLibrary("google.dagger").get())
                add("ksp", libs.findLibrary("google.dagger.compiler").get())
            }
        }
    }

}