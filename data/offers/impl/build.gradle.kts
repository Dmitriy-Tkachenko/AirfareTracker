plugins {
    alias(libs.plugins.app.android.data)
}

android {
    namespace = "ru.tk4dmitriy.data.offers.impl"
}

dependencies {
    implementation(projects.data.offers.api)
}