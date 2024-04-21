@file:Suppress("UnstableApiUsage")

plugins {
    `kotlin-dsl`
}

group = "ru.tk4dmitriy.build_logic.convention.gradle_plugins"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(libs.versions.jvm.get()))
    }
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "app.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "app.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidScreen") {
            id = "app.android.screen"
            implementationClass = "AndroidScreenConventionPlugin"
        }
        register("androidCoreUtils") {
            id = "app.android.core.utils"
            implementationClass = "AndroidCoreUtilsConventionPlugin"
        }
        register("kotlinLibrary") {
            id = "app.kotlin.library"
            implementationClass = "KotlinLibraryConventionPlugin"
        }
    }
}