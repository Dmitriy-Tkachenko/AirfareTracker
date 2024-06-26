import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import ru.tk4dmitriy.build_logic.convention.gradle_plugins.applicationExtension
import ru.tk4dmitriy.build_logic.convention.gradle_plugins.configureKotlin
import ru.tk4dmitriy.build_logic.convention.gradle_plugins.configureKotlinAndroid
import ru.tk4dmitriy.build_logic.convention.gradle_plugins.libs

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.run {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("com.google.devtools.ksp")
            }

            requireNotNull(applicationExtension).apply {
                buildFeatures {
                    viewBinding = true
                }
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = libs.findVersion("targetSdk").get().toString().toInt()
                configureKotlin()
            }
        }
    }
}