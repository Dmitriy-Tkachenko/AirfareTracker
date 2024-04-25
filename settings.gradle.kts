@file:Suppress("UnstableApiUsage")

include(":data:tickets:impl")


include(":data:tickets:api")


include(":features:tickets:impl")


include(":features:tickets:api")


include(":data:offers_tickets:impl")


include(":data:offers_tickets:api")


include(":features:offers_tickets:impl")


include(":features:offers_tickets:api")


enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")

    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AirfareTracker"
include(":app")
include(":module_injector")
include(":core:res")
include(":core:utils")
include(":data:departure_place:impl")
include(":data:departure_place:api")
include(":data:offers:impl")
include(":data:offers:api")
include(":features:departure_place:impl")
include(":features:departure_place:api")
include(":features:offers:impl")
include(":features:offers:api")
include(":screens:airfares")
include(":screens:hotels")
include(":screens:closer")
include(":screens:subscriptions")
include(":screens:profile")
