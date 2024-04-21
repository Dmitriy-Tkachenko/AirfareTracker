package ru.tk4dmitriy.screens.closer.starter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import ru.tk4dmitriy.screens.closer.CloserFragment
import ru.tk4dmitriy.screens.closer.api.CloserStarter
import javax.inject.Inject

private const val TAG = "CLOSER_FRAGMENT"

internal class CloserStarterImpl @Inject constructor() : CloserStarter {
    override fun getTag(): String = TAG

    override fun addFragment(containerViewId: Int, transaction: FragmentTransaction) {
        transaction.add(containerViewId, CloserFragment(), TAG)
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