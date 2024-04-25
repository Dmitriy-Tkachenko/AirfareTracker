plugins {
    alias(libs.plugins.app.android.data)
}

android {
    namespace = "ru.tk4dmitriy.data.offers_tickets.impl"
}

dependencies {
    implementation(projects.data.offersTickets.api)
}