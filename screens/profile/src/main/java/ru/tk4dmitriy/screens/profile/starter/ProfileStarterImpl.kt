package ru.tk4dmitriy.screens.profile.starter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import ru.tk4dmitriy.screens.profile.ProfileFragment
import ru.tk4dmitriy.screens.profile.api.ProfileStarter
import javax.inject.Inject

private const val TAG = "PROFILE_FRAGMENT"

class ProfileStarterImpl @Inject constructor() : ProfileStarter {
    override fun getTag(): String = TAG

    override fun addFragment(containerViewId: Int, transaction: FragmentTransaction) {
        transaction.add(containerViewId, ProfileFragment(), TAG)
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