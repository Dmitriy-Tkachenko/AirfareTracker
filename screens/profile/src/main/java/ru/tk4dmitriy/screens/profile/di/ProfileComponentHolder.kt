package ru.tk4dmitriy.screens.profile.di

import ru.tk4dmitriy.module_injector.ComponentHolder
import ru.tk4dmitriy.screens.profile.api.ProfileScreenApi

object ProfileComponentHolder : ComponentHolder<ProfileScreenApi, ProfileComponentDependencies> {
    private var profileComponent: ProfileComponent? = null

    override fun init(dependencies: ProfileComponentDependencies) {
        if (profileComponent == null) {
            synchronized(ProfileComponentHolder::class.java) {
                if (profileComponent == null) {
                    profileComponent = ProfileComponent.initAndGet(dependencies = dependencies)
                }
            }
        }
    }

    override fun get(): ProfileScreenApi = getComponent()

    internal fun getComponent(): ProfileComponent {
        checkNotNull(profileComponent) { "ProfileComponent was not initialized!" }
        return profileComponent!!
    }

    override fun reset() {
        profileComponent = null
    }
}