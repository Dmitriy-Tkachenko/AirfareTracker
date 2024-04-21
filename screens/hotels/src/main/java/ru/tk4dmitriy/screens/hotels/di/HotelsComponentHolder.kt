package ru.tk4dmitriy.screens.hotels.di

import ru.tk4dmitriy.module_injector.ComponentHolder
import ru.tk4dmitriy.screens.hotels.api.HotelsScreenApi

object HotelsComponentHolder : ComponentHolder<HotelsScreenApi, HotelsComponentDependencies> {
    private var hotelsComponent: HotelsComponent? = null

    override fun init(dependencies: HotelsComponentDependencies) {
        if (hotelsComponent == null) {
            synchronized(HotelsComponentHolder::class.java) {
                if (hotelsComponent == null) {
                    hotelsComponent = HotelsComponent.initAndGet(dependencies = dependencies)
                }
            }
        }
    }

    override fun get(): HotelsScreenApi = getComponent()

    internal fun getComponent(): HotelsComponent {
        checkNotNull(hotelsComponent) { "HotelsComponent was not initialized!" }
        return hotelsComponent!!
    }

    override fun reset() {
        hotelsComponent = null
    }
}