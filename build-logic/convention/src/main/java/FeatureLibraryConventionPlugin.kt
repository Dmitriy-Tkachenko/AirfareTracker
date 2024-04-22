import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import ru.tk4dmitriy.build_logic.convention.gradle_plugins.libs

class FeatureLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.run {
                apply("app.kotlin.library")
            }

            dependencies {
                add("implementation", libs.findLibrary("javax.inject").get())
            }
        }
    }
}