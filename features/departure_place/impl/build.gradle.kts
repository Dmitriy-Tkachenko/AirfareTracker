plugins {
    alias(libs.plugins.app.feature.library)
}

dependencies {
    implementation(projects.features.departurePlace.api)
    implementation(projects.data.departurePlace.api)
}