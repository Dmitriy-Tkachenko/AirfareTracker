plugins {
    alias(libs.plugins.app.android.data)
}

android {
    namespace = "ru.tk4dmitriy.data.departure_place.impl"
}

dependencies {
    implementation(projects.data.departurePlace.api)
}