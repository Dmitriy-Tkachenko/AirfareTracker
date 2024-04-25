package ru.tk4dmitriy.airfaretracker.utils

import android.content.Context
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import ru.tk4dmitriy.airfaretracker.R
import ru.tk4dmitriy.airfaretracker.app.App
import ru.tk4dmitriy.screens.airfares.api.AirfaresScreenApi
import ru.tk4dmitriy.screens.closer.api.CloserScreenApi
import ru.tk4dmitriy.screens.hotels.api.HotelsScreenApi
import ru.tk4dmitriy.screens.profile.api.ProfileScreenApi
import ru.tk4dmitriy.screens.subscriptions.api.SubscriptionsScreenApi
import javax.inject.Inject
import javax.inject.Provider

class MyCustomNavHostFragment : NavHostFragment() {
    @Inject
    lateinit var airfaresScreenApi: Provider<AirfaresScreenApi>
    @Inject
    lateinit var hotelsScreenApi: Provider<HotelsScreenApi>
    @Inject
    lateinit var closerScreenApi: Provider<CloserScreenApi>
    @Inject
    lateinit var subscriptionsScreenApi: Provider<SubscriptionsScreenApi>
    @Inject
    lateinit var profileScreenApi: Provider<ProfileScreenApi>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as App).appComponent.inject(this)
    }

    override fun onCreateNavHostController(navHostController: NavHostController) {
        super.onCreateNavHostController(navHostController)
        navHostController.addOnDestinationChangedListener { _,destination,_ ->
            when (destination.id) {
                R.id.tab_airfares -> airfaresScreenApi.get().getTag()
                R.id.tab_hotels -> hotelsScreenApi.get().getTag()
                R.id.tab_closer -> closerScreenApi.get().getTag()
                R.id.tab_subscriptions -> subscriptionsScreenApi.get().getTag()
                R.id.tab_profile -> profileScreenApi.get().getTag()
            }
        }
    }
}