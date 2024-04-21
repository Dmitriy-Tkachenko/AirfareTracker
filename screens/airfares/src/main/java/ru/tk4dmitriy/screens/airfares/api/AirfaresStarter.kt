package ru.tk4dmitriy.screens.airfares.api

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

interface AirfaresStarter {
    fun getTag(): String
    fun addFragment(
        @IdRes containerViewId: Int,
        transaction: FragmentTransaction,
    )
    fun showOrAddFragment(
        @IdRes containerViewId: Int,
        fragmentManager: FragmentManager,
        transaction: FragmentTransaction,
    )
}