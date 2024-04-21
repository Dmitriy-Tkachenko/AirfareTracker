package ru.tk4dmitriy.screens.airfares.di

import ru.tk4dmitriy.module_injector.ComponentHolder
import ru.tk4dmitriy.screens.airfares.api.AirfaresScreenApi

object AirfaresComponentHolder : ComponentHolder<AirfaresScreenApi, AirfaresComponentDependencies> {
    private var airfaresComponent: AirfaresComponent? = null

    override fun init(dependencies: AirfaresComponentDependencies) {
        if (airfaresComponent == null) {
            synchronized(AirfaresComponentHolder::class.java) {
                if (airfaresComponent == null) {
                    airfaresComponent = AirfaresComponent.initAndGet(dependencies = dependencies)
                }
            }
        }
    }

    override fun get(): AirfaresScreenApi = getComponent()

    internal fun getComponent(): AirfaresComponent {
        checkNotNull(airfaresComponent) { "AirfaresComponent was not initialized!" }
        return airfaresComponent!!
    }

    override fun reset() {
        airfaresComponent = null
    }
}