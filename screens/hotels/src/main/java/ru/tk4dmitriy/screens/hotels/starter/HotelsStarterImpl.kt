package ru.tk4dmitriy.screens.hotels.starter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import ru.tk4dmitriy.screens.hotels.HotelsFragment
import ru.tk4dmitriy.screens.hotels.api.HotelsStarter
import javax.inject.Inject

private const val TAG = "HOTELS_FRAGMENT"

internal class HotelsStarterImpl @Inject constructor() : HotelsStarter {
    override fun getTag(): String = TAG

    override fun addFragment(containerViewId: Int, transaction: FragmentTransaction) {
        transaction.add(containerViewId, HotelsFragment(), TAG)
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