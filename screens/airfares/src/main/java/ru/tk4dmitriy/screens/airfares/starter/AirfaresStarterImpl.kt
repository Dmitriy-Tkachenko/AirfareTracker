package ru.tk4dmitriy.screens.airfares.starter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import ru.tk4dmitriy.screens.airfares.ui.AirfaresFragment
import ru.tk4dmitriy.screens.airfares.api.AirfaresStarter
import javax.inject.Inject

private const val TAG = "AIRFARES_FRAGMENT"

class AirfaresStarterImpl @Inject constructor() : AirfaresStarter {
    override fun getTag(): String = TAG

    override fun addFragment(containerViewId: Int, transaction: FragmentTransaction) {
        transaction.add(containerViewId, AirfaresFragment(), TAG)
    }

    override fun showOrAddFragment(
        containerViewId: Int,
        fragmentManager: FragmentManager,
        transaction: FragmentTransaction
    ) {
        fragmentManager.findFragmentByTag(TAG)?.let {
            transaction.show(it)
        } ?: addFragment(containerViewId, transaction)
    }
}