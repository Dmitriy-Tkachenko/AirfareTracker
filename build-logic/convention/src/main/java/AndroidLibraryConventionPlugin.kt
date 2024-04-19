import org.gradle.api.Plugin
import org.gradle.api.Project
import ru.tk4dmitriy.build_logic.convention.gradle_plugins.configureKotlin
import ru.tk4dmitriy.build_logic.convention.gradle_plugins.configureKotlinAndroid
import ru.tk4dmitriy.build_logic.convention.gradle_plugins.libraryExtension

class AndroidLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.run {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            requireNotNull(libraryExtension).apply {
                configureKotlinAndroid(this)
                configureKotlin()
            }
        }
    }

}