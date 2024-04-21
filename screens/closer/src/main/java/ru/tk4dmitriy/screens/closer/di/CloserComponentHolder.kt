package ru.tk4dmitriy.screens.closer.di

import ru.tk4dmitriy.module_injector.ComponentHolder
import ru.tk4dmitriy.screens.closer.api.CloserScreenApi

object CloserComponentHolder : ComponentHolder<CloserScreenApi, CloserComponentDependencies> {
    private var closerComponent: CloserComponent? = null

    override fun init(dependencies: CloserComponentDependencies) {
        if (closerComponent == null) {
            synchronized(CloserComponentHolder::class.java) {
                if (closerComponent == null) {
                    closerComponent = CloserComponent.initAndGet(dependencies = dependencies)
                }
            }
        }
    }

    override fun get(): CloserScreenApi = getComponent()

    internal fun getComponent(): CloserComponent {
        checkNotNull(closerComponent) { "CloserComponent was not initialized!" }
        return closerComponent!!
    }

    override fun reset() {
        closerComponent = null
    }
}