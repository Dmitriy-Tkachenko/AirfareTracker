plugins {
    alias(libs.plugins.app.android.screen)
}

android {
    namespace = "ru.tk4dmitriy.screens.airfares"
}

dependencies {
    implementation(projects.features.departurePlace.api)
    implementation(projects.features.offers.api)
    implementation(libs.androidx.fragment.ktx)
}