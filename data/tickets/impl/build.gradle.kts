plugins {
    alias(libs.plugins.app.android.data)
}


android {
    namespace = "ru.tk4dmitriy.data.tickets.impl"
}

dependencies {
    implementation(projects.data.tickets.api)
}