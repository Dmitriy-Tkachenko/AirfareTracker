package ru.tk4dmitriy.airfaretracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationBarView
import ru.tk4dmitriy.airfaretracker.app.App
import ru.tk4dmitriy.airfaretracker.databinding.ActivityMainBinding
import ru.tk4dmitriy.screens.airfares.api.AirfaresScreenApi
import ru.tk4dmitriy.screens.closer.api.CloserScreenApi
import ru.tk4dmitriy.screens.hotels.api.HotelsScreenApi
import ru.tk4dmitriy.screens.profile.api.ProfileScreenApi
import ru.tk4dmitriy.screens.subscriptions.api.SubscriptionsScreenApi
import javax.inject.Inject
import javax.inject.Provider

private const val CURRENT_FRAGMENT_TAG = "CURRENT_FRAGMENT_TAG"
class MainActivity : AppCompatActivity() {
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

    private lateinit var binding: ActivityMainBinding
    private var currentFragmentTag: String = ""
    private var airfaresFragmentTag: String = ""
    private var hotelsFragmentTag: String = ""
    private var closerFragmentTag: String = ""
    private var subscriptionsFragmentTag: String = ""
    private var profileFragmentTag: String = ""

    private val onItemSelectedListener = NavigationBarView.OnItemSelectedListener { menuItem ->
        val tag = when (menuItem.itemId) {
            R.id.tab_airfares -> airfaresFragmentTag
            R.id.tab_hotels -> hotelsFragmentTag
            R.id.tab_closer -> closerFragmentTag
            R.id.tab_subscriptions -> subscriptionsFragmentTag
            R.id.tab_profile -> profileFragmentTag
            else -> return@OnItemSelectedListener false
        }
        loadFragment(tag)
        return@OnItemSelectedListener true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (application as App).appComponent.inject(mainActivity = this)

        airfaresFragmentTag = airfaresScreenApi.get().getAirfaresStarter().getTag()
        hotelsFragmentTag = hotelsScreenApi.get().getHotelsStarter().getTag()
        closerFragmentTag = closerScreenApi.get().getCloserStarter().getTag()
        subscriptionsFragmentTag = subscriptionsScreenApi.get().getSubscriptionsStarter().getTag()
        profileFragmentTag = profileScreenApi.get().getProfileStarter().getTag()

        binding.bottomNav.setOnItemSelectedListener(onItemSelectedListener)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                airfaresScreenApi
                    .get()
                    .getAirfaresStarter()
                    .addFragment(containerViewId = R.id.fragment_container, transaction = this)
                currentFragmentTag = airfaresFragmentTag
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        currentFragmentTag = savedInstanceState.getString(CURRENT_FRAGMENT_TAG, "")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(CURRENT_FRAGMENT_TAG, currentFragmentTag)
        super.onSaveInstanceState(outState)
    }

    private fun loadFragment(tag: String) {
        supportFragmentManager.commit {
            hideCurrentFragment()
            when (tag) {
                airfaresFragmentTag -> airfaresScreenApi
                    .get()
                    .getAirfaresStarter()
                    .showOrAddFragment(R.id.fragment_container, supportFragmentManager, this)
                hotelsFragmentTag -> hotelsScreenApi
                    .get()
                    .getHotelsStarter()
                    .showOrAddFragment(R.id.fragment_container, supportFragmentManager, this)
                closerFragmentTag -> closerScreenApi
                    .get()
                    .getCloserStarter()
                    .showOrAddFragment(R.id.fragment_container, supportFragmentManager, this)
                subscriptionsFragmentTag -> subscriptionsScreenApi
                    .get()
                    .getSubscriptionsStarter()
                    .showOrAddFragment(R.id.fragment_container, supportFragmentManager, this)
                profileFragmentTag -> profileScreenApi
                    .get()
                    .getProfileStarter()
                    .showOrAddFragment(R.id.fragment_container, supportFragmentManager, this)
            }
            currentFragmentTag = tag
        }
    }

    private fun FragmentTransaction.hideCurrentFragment() {
        supportFragmentManager.findFragmentByTag(currentFragmentTag)?.let {
            hide(it)
        }
    }
}