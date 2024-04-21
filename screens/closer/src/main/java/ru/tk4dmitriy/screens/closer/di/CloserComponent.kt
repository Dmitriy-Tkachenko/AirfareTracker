package ru.tk4dmitriy.screens.closer.di

import dagger.Component
import ru.tk4dmitriy.screens.closer.CloserFragment
import ru.tk4dmitriy.screens.closer.api.CloserScreenApi

@Component(
    dependencies = [CloserComponentDependencies::class],
    modules = [CloserStarterModule::class]
)
internal abstract class CloserComponent : CloserScreenApi {
    abstract fun inject(fragment: CloserFragment)

    companion object {
        fun initAndGet(dependencies: CloserComponentDependencies): CloserComponent {
            return DaggerCloserComponent.builder()
                .closerComponentDependencies(dependencies)
                .build()
        }
    }
}
