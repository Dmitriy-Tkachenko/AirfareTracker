plugins {
    alias(libs.plugins.app.android.application)
}

android {
    namespace = "ru.tk4dmitriy.airfaretracker"

    defaultConfig {
        applicationId = "ru.tk4dmitriy.airfaretracker"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.google.material)
}