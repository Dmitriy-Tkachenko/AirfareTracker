plugins {
    alias(libs.plugins.app.feature.library)
}

dependencies {
    implementation(projects.features.offers.api)
    implementation(projects.data.offers.api)
}