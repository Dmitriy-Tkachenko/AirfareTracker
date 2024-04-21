package ru.tk4dmitriy.screens.subscriptions.starter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import ru.tk4dmitriy.screens.subscriptions.SubscriptionsFragment
import ru.tk4dmitriy.screens.subscriptions.api.SubscriptionsStarter
import javax.inject.Inject

private const val TAG = "SUBSCRIPTIONS_FRAGMENT"

class SubscriptionsStarterImpl @Inject constructor() : SubscriptionsStarter {
    override fun getTag(): String = TAG

    override fun addFragment(containerViewId: Int, transaction: FragmentTransaction) {
        transaction.add(containerViewId, SubscriptionsFragment(), TAG)
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