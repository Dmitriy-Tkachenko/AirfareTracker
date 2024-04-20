plugins {
    alias(libs.plugins.app.android.library)
}

android {
    namespace = "ru.tk4dmitriy.core.res"
}

dependencies {
    implementation(libs.google.material)
}