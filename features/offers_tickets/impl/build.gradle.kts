plugins {
    alias(libs.plugins.app.feature.library)
}

dependencies {
    implementation(projects.features.offersTickets.api)
    implementation(projects.data.offersTickets.api)
}
