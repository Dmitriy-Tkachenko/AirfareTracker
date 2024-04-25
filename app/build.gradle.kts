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
    implementation(projects.moduleInjector)
    implementation(projects.core.res)
    implementation(projects.data.departurePlace.api)
    implementation(projects.data.departurePlace.impl)
    implementation(projects.data.offers.api)
    implementation(projects.data.offers.impl)
    implementation(projects.data.offersTickets.api)
    implementation(projects.data.offersTickets.impl)
    implementation(projects.features.departurePlace.api)
    implementation(projects.features.departurePlace.impl)
    implementation(projects.features.offers.api)
    implementation(projects.features.offers.impl)
    implementation(projects.features.offersTickets.api)
    implementation(projects.features.offersTickets.impl)
    implementation(projects.screens.airfares)
    implementation(projects.screens.hotels)
    implementation(projects.screens.closer)
    implementation(projects.screens.subscriptions)
    implementation(projects.screens.profile)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.google.material)
    implementation(libs.google.dagger)
    ksp(libs.google.dagger.compiler)
}