plugins {
    alias(libs.plugins.app.feature.library)
}

dependencies {
    implementation(projects.features.tickets.api)
    implementation(projects.data.tickets.api)
}
