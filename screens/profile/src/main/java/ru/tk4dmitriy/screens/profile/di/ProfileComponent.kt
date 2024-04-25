package ru.tk4dmitriy.screens.profile.di

import dagger.Component
import ru.tk4dmitriy.screens.profile.ProfileFragment
import ru.tk4dmitriy.screens.profile.api.ProfileScreenApi

@Component(
    dependencies = [ProfileComponentDependencies::class]
)
internal abstract class ProfileComponent : ProfileScreenApi {
    abstract fun inject(fragment: ProfileFragment)

    companion object {
        fun initAndGet(dependencies: ProfileComponentDependencies): ProfileComponent {
            return DaggerProfileComponent.builder()
                .profileComponentDependencies(dependencies)
                .build()
        }
    }
}
