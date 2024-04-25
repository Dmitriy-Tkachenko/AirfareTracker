package ru.tk4dmitriy.screens.hotels.di

import dagger.Component
import ru.tk4dmitriy.screens.hotels.HotelsFragment
import ru.tk4dmitriy.screens.hotels.api.HotelsScreenApi

@Component(
    dependencies = [HotelsComponentDependencies::class]
)
internal abstract class HotelsComponent : HotelsScreenApi {
    abstract fun inject(fragment: HotelsFragment)

    companion object {
        fun initAndGet(dependencies: HotelsComponentDependencies): HotelsComponent {
            return DaggerHotelsComponent.builder()
                .hotelsComponentDependencies(dependencies)
                .build()
        }
    }
}
