package ru.tk4dmitriy.screens.profile.di

import dagger.Component
import ru.tk4dmitriy.screens.profile.ProfileFragment
import ru.tk4dmitriy.screens.profile.api.ProfileScreenApi
import ru.tk4dmitriy.screens.profile.api.ProfileStarter

@Component(
    dependencies = [ProfileComponentDependencies::class],
    modules = [ProfileStarterModule::class]
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
